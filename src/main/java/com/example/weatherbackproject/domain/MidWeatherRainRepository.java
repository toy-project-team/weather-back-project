package com.example.weatherbackproject.domain;

import java.util.List;

public interface MidWeatherRainRepository {

    MidWeatherRain save(MidWeatherRain midWeatherRain);

    List<MidWeatherRain> findAllByInquiryDate(String date);
}
