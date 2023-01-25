package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MidWeatherTemperatureRepository extends JpaRepository<MidWeatherTemperature, Long> {
    List<MidWeatherTemperature> findAllByInquiryDate(String date);

    Optional<MidWeatherTemperature> findByRegionCodeIdAndInquiryDate(Long codeId, String date);
}
