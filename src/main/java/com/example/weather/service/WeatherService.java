package com.example.weather.service;

import com.example.weather.dto.GeoZipResponse;
import com.example.weather.dto.WeatherApiResponse;
import com.example.weather.entity.PincodeLocation;
import com.example.weather.entity.WeatherInfo;
import com.example.weather.repository.PincodeLocationRepository;
import com.example.weather.repository.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class WeatherService {

    private final PincodeLocationRepository pincodeRepo;
    private final WeatherInfoRepository weatherRepo;
    private final RestTemplate restTemplate;

    @Value("${openweather.base-url}")
    private String baseUrl;

    @Value("${openweather.api-key}")
    private String apiKey;

    @Value("${openweather.units:metric}")
    private String units;

    @Autowired
    public WeatherService(PincodeLocationRepository pincodeRepo,
                          WeatherInfoRepository weatherRepo,
                          RestTemplate restTemplate) {
        this.pincodeRepo = pincodeRepo;
        this.weatherRepo = weatherRepo;
        this.restTemplate = restTemplate;
    }

    public WeatherInfo getWeather(String pincode, LocalDate forDate, String country) {
        // 1) Check DB first
        Optional<WeatherInfo> cached = weatherRepo.findByPincodeAndForDate(pincode, forDate);
        if (cached.isPresent()) {
            return cached.get();
        }

        // 2) Only fetch live weather for today (OpenWeather Current API)
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        if (!forDate.equals(today)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "No cached weather for " + forDate + ". Current API supports only today's data.");
        }

        // 3) Find or fetch location (lat/lon)
        PincodeLocation loc = pincodeRepo.findById(pincode)
                .orElseGet(() -> fetchAndSaveLocation(pincode, country));

        // 4) Call weather API
        String weatherUrl = String.format(
                "%s/data/2.5/weather?lat=%s&lon=%s&units=%s&appid=%s",
                baseUrl, loc.getLat(), loc.getLon(), units, apiKey);

        WeatherApiResponse response = restTemplate.getForObject(weatherUrl, WeatherApiResponse.class);
        if (response == null || response.getMain() == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY, "Empty weather response");
        }

        // 5) Save into DB
        WeatherInfo wi = new WeatherInfo();
        wi.setPincode(pincode);
        wi.setForDate(forDate);
        wi.setTemperature(response.getMain().getTemp());
        wi.setCondition(response.getWeather() != null && !response.getWeather().isEmpty()
                ? response.getWeather().get(0).getMain() + " - " + response.getWeather().get(0).getDescription()
                : "N/A");
        wi.setWindSpeed(response.getWind() != null ? response.getWind().getSpeed() : 0.0);
        wi.setHumidity(response.getMain().getHumidity());
        wi.setTimestamp(response.getDt());

        return weatherRepo.save(wi);
    }

    private PincodeLocation fetchAndSaveLocation(String pincode, String country) {
        String geoUrl = String.format(
                "%s/geo/1.0/zip?zip=%s,%s&appid=%s",
                baseUrl, pincode, country, apiKey);

        GeoZipResponse geo = restTemplate.getForObject(geoUrl, GeoZipResponse.class);
        if (geo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY, "Empty geocoding response");
        }

        PincodeLocation loc = new PincodeLocation();
        loc.setPincode(pincode);
        loc.setLat(geo.getLat());
        loc.setLon(geo.getLon());
        loc.setCountry(geo.getCountry());

        return pincodeRepo.save(loc);
    }
}
