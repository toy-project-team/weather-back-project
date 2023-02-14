package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMidWeatherTemperatureRepository extends MidWeatherTemperatureRepository, JpaRepository<MidWeatherTemperature, Long> {
}
