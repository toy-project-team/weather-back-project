package com.example.weatherbackproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public Optional<MidWeatherCloud> findByRegionCodeIdAndInquiryDate(Long regionCodeId, String date) {
        return midWeatherClouds.values().stream()
                .filter(midWeatherCloud -> midWeatherCloud.equalsCodeAndDate(regionCodeId, date))
                .findFirst();
    }
}
