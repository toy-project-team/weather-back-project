package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MidWeatherRainRepository extends JpaRepository<MidWeatherRain, Long> {
    List<MidWeatherRain> findAllByInquiryDate(String date);

    Optional<MidWeatherRain> findByRegionCodeIdAndInquiryDate(Long codeId, String date);
}
