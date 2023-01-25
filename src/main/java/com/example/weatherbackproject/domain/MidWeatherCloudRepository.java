package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MidWeatherCloudRepository extends JpaRepository<MidWeatherCloud, Long> {
    List<MidWeatherCloud> findAllByInquiryDate(String date);

    Optional<MidWeatherCloud> findByRegionCodeIdAndInquiryDate(Long codeId, String date);
}
