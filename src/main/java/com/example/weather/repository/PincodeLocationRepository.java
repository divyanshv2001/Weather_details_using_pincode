package com.example.weather.repository;

import com.example.weather.entity.PincodeLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PincodeLocationRepository extends JpaRepository<PincodeLocation, String> {
}
