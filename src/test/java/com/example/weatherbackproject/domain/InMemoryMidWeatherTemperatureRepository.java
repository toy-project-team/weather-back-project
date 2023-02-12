package com.example.weatherbackproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMidWeatherTemperatureRepository implements MidWeatherTemperatureRepository {
    private final Map<Long, MidWeatherTemperature> midWeatherTemperatures = new HashMap<>();

    @Override
    public MidWeatherTemperature save(MidWeatherTemperature midWeatherTemperature) {
        midWeatherTemperatures.put(midWeatherTemperature.getId(), midWeatherTemperature);
        return midWeatherTemperature;
    }

    @Override
    public List<MidWeatherTemperature> findAllByInquiryDate(String date) {
        return midWeatherTemperatures.values().stream()
                .filter(midWeatherTemperature -> midWeatherTemperature.getInquiryDate().equals(date))
                .toList();
    }

    @Override
    public Optional<MidWeatherTemperature> findByRegionCodeIdAndInquiryDate(Long regionCodeId, String date) {
        return midWeatherTemperatures.values().stream()
                .filter(midWeatherTemperature -> midWeatherTemperature.equalsCodeAndDate(regionCodeId, date))
                .findFirst();
    }
}
