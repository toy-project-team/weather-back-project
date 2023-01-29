package com.example.weatherbackproject.domain;

import java.util.ArrayList;
import java.util.List;

public class MidWeatherClouds {

    private List<MidWeatherCloud> midWeatherClouds = new ArrayList<>();

    public MidWeatherClouds(List<MidWeatherCloud> midWeatherClouds) {
        this.midWeatherClouds = midWeatherClouds;
    }

    public MidWeatherCloud findMidWeatherCloud(Long codeId, String date) {
        return midWeatherClouds.stream()
                .filter(cloud -> cloud.equalsCodeAndDate(codeId, date))
                .findFirst()
                .orElseThrow();
    }
}
