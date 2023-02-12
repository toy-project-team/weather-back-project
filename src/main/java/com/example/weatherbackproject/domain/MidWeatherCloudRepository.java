package com.example.weatherbackproject.domain;

import java.util.List;
import java.util.Optional;

public interface MidWeatherCloudRepository {

    MidWeatherCloud save(MidWeatherCloud midWeatherCloud);

    List<MidWeatherCloud> findAllByInquiryDate(String date);

    Optional<MidWeatherCloud> findByRegionCodeIdAndInquiryDate(Long regionCodeId, String date);
}
