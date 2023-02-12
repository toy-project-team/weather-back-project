package com.example.weatherbackproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMidWeatherRainRepository implements MidWeatherRainRepository {
    private final Map<Long, MidWeatherRain> midWeatherRains = new HashMap<>();

    @Override
    public MidWeatherRain save(MidWeatherRain midWeatherRain) {
        midWeatherRains.put(midWeatherRain.getId(), midWeatherRain);
        return midWeatherRain;
    }

    @Override
    public List<MidWeatherRain> findAllByInquiryDate(String date) {
        return midWeatherRains.values().stream()
                .filter(midWeatherRain -> midWeatherRain.getInquiryDate().equals(date))
                .toList();
    }

    @Override
    public Optional<MidWeatherRain> findByRegionCodeIdAndInquiryDate(Long regionCodeId, String date) {
        return midWeatherRains.values().stream()
                .filter(midWeatherRain -> midWeatherRain.equalsCodeAndDate(regionCodeId, date))
                .findFirst();
    }
}
