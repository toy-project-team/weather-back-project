package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MidWeatherCloudRepository extends JpaRepository<MidWeatherCloud, Long> {
    List<MidWeatherCloud> findAllByInquiryDate(String date);
}
