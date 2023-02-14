package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShortWeatherRepository extends ShortWeatherRepository, JpaRepository<ShortWeather, Long> {
}
