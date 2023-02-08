package com.example.weatherbackproject.domain;

import java.util.List;

public interface MidWeatherTemperatureRepository {

    MidWeatherTemperature save(MidWeatherTemperature midWeatherTemperature);

    List<MidWeatherTemperature> findAllByInquiryDate(String date);
}
