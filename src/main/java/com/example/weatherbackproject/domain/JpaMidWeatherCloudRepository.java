package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMidWeatherCloudRepository extends MidWeatherCloudRepository, JpaRepository<MidWeatherCloud, Long> {
}
