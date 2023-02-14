package com.example.weatherbackproject.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryShortWeatherRepository implements ShortWeatherRepository {
    private final Map<Long, ShortWeather> shortWeathers = new HashMap<>();

    @Override
    public ShortWeather save(ShortWeather shortWeather) {
        shortWeathers.put(shortWeathers.size() + 1L, shortWeather);
        return shortWeather;
    }

    @Override
    public List<ShortWeather> findByRegionCodeIdAndInquiryDateGreaterThanEqualOrderByInquiryDateAsc(Long regionCodeId, LocalDateTime date) {
        return shortWeathers.values().stream()
                .filter(shortWeather -> shortWeather.getRegionCodeId().equals(regionCodeId) && shortWeather.getInquiryDate().compareTo(date) >= 0)
                .toList();
    }
}
