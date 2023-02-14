package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMidWeatherRainRepository extends MidWeatherRainRepository, JpaRepository<MidWeatherRain, Long> {
}
