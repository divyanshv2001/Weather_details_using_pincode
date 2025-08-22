package com.example.weather.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class WeatherRequest {

    @NotBlank(message = "pincode is required")
    private String pincode;

    @NotNull(message = "for_date is required (yyyy-MM-dd)")
    @JsonProperty("for_date")                   
    @JsonAlias({"forDate","date"})              
    @JsonFormat(pattern = "yyyy-MM-dd")         
    private LocalDate forDate;

    private String country = "IN";

    // getters & setters
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public LocalDate getForDate() { return forDate; }
    public void setForDate(LocalDate forDate) { this.forDate = forDate; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
