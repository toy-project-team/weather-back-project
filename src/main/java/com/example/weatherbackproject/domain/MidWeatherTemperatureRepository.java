package com.example.weatherbackproject.domain;

import java.util.List;
import java.util.Optional;

public interface MidWeatherTemperatureRepository {

    MidWeatherTemperature save(MidWeatherTemperature midWeatherTemperature);

    List<MidWeatherTemperature> findAllByInquiryDate(String date);

    Optional<MidWeatherTemperature> findByRegionCodeIdAndInquiryDate(Long regionCodeId, String date);
}
