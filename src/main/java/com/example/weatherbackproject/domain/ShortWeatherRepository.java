package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShortWeatherRepository extends JpaRepository<ShortWeather, Long>, ShortWeatherRepositoryCustom {

    List<ShortWeather> findByRegionCodeIdAndInquiryDateGreaterThanEqual(Long regionCodeId, LocalDateTime date);
}
