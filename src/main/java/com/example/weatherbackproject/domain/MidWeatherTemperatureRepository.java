package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MidWeatherTemperatureRepository extends JpaRepository<MidWeatherTemperature, Long> {
    List<MidWeatherTemperature> findAllByInquiryDate(String date);
}
