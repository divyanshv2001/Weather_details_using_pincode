package com.example.weather.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pincode_location")
public class PincodeLocation {

    @Id
    private String pincode;

    private double lat;
    private double lon;
    private String country;

    // Getters & Setters
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
