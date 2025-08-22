package com.example.weather.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "weather_info",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_pincode_date", columnNames = {"pincode", "for_date"})
    }
)
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String pincode;

    @Column(name = "for_date", nullable = false)
    private LocalDate forDate;

    private double temperature;

    @Column(name = "weather_condition")
    private String condition;

    private double windSpeed;
    private int humidity;

    @Column(name = "observed_at_unix")
    private long timestamp;

    // ---- getters and setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public LocalDate getForDate() { return forDate; }
    public void setForDate(LocalDate forDate) { this.forDate = forDate; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
