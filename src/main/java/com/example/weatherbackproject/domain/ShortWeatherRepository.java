package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortWeatherRepository extends JpaRepository<ShortWeather, Long>, ShortWeatherRepositoryCustom {
}
