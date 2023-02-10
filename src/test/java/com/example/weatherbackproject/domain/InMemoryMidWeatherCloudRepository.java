package com.example.weatherbackproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMidWeatherCloudRepository implements MidWeatherCloudRepository {
    private final Map<Long, MidWeatherCloud> midWeatherClouds = new HashMap<>();

    @Override
    public MidWeatherCloud save(MidWeatherCloud midWeatherCloud) {
        midWeatherClouds.put(midWeatherCloud.getId(), midWeatherCloud);
        return midWeatherCloud;
    }

    @Override
    public List<MidWeatherCloud> findAllByInquiryDate(String date) {
        return midWeatherClouds.values().stream()
                .filter(midWeatherCloud -> midWeatherCloud.getInquiryDate().equals(date))
                .toList();
    }
}
