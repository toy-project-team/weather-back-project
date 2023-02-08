package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMidWeatherTemperatureRepository extends MidWeatherTemperatureRepository, JpaRepository<MidWeatherTemperature, Long> {

    Optional<MidWeatherTemperature> findByRegionCodeIdAndInquiryDate(Long codeId, String date);
}
