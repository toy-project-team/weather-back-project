package com.example.weatherbackproject.domain;

import java.util.List;
import java.util.Optional;

public interface MidWeatherRainRepository {

    MidWeatherRain save(MidWeatherRain midWeatherRain);

    List<MidWeatherRain> findAllByInquiryDate(String date);

    Optional<MidWeatherRain> findByRegionCodeIdAndInquiryDate(Long regionCodeId, String date);
}
