package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMidWeatherCloudRepository extends MidWeatherCloudRepository, JpaRepository<MidWeatherCloud, Long> {

    Optional<MidWeatherCloud> findByRegionCodeIdAndInquiryDate(Long codeId, String date);
}
