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
    public Optional<MidWeatherRain> findById(Long id) {
        return Optional.ofNullable(midWeatherRains.get(id));
    }

    @Override
    public List<MidWeatherRain> findAllByInquiryDate(String date) {
        return midWeatherRains.values().stream()
                .filter(midWeatherRain -> midWeatherRain.getInquiryDate().equals(date))
                .toList();
    }
}
