package com.example.weatherbackproject.domain;

import java.util.List;
import java.util.Optional;

public interface MidWeatherRainRepository {

    MidWeatherRain save(MidWeatherRain midWeatherRain);

    Optional<MidWeatherRain> findById(Long id);

    List<MidWeatherRain> findAllByInquiryDate(String date);
}
