package com.example.weather.controller;

import com.example.weather.dto.WeatherRequest;
import com.example.weather.entity.WeatherInfo;
import com.example.weather.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
    @PostMapping(value = "/weather", consumes = "application/json", produces = "application/json")
    public ResponseEntity<WeatherInfo> getWeather(@Valid @RequestBody WeatherRequest request) {
        WeatherInfo wi = weatherService.getWeather(
                request.getPincode(),
                request.getForDate(),
                (request.getCountry() != null && !request.getCountry().isEmpty())
                        ? request.getCountry().toUpperCase()
                        : "IN"
        );
        return ResponseEntity.ok(wi);
    }
}
