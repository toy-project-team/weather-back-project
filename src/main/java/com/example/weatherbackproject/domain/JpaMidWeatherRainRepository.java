package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMidWeatherRainRepository extends MidWeatherRainRepository, JpaRepository<MidWeatherRain, Long> {

    Optional<MidWeatherRain> findByRegionCodeIdAndInquiryDate(Long codeId, String date);
}
