package com.example.weather.dto;
import java.util.List;

public class WeatherApiResponse {
    private String name;
    private long dt;
    private Main main;
    private List<Weather> weather;
    private Wind wind;

    public static class Main {
        private double temp;
        private int humidity;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }
        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    public static class Weather {
        private String main;
        private String description;

        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class Wind {
        private double speed;

        public double getSpeed() { return speed; }
        public void setSpeed(double speed) { this.speed = speed; }
    }

    // getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getDt() { return dt; }
    public void setDt(long dt) { this.dt = dt; }
    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }
    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }
    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }
}
