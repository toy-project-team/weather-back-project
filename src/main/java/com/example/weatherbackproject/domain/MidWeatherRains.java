package com.example.weatherbackproject.domain;

import java.util.ArrayList;
import java.util.List;

public class MidWeatherRains {

    private List<MidWeatherRain> midWeatherRains = new ArrayList<>();

    public MidWeatherRains(List<MidWeatherRain> midWeatherRains) {
        this.midWeatherRains = midWeatherRains;
    }

    public MidWeatherRain findMidWeatherRain(Long codeId ,String date) {
        return midWeatherRains.stream()
                .filter(rain -> rain.equalsCodeAndDate(codeId, date))
                .findFirst()
                .orElseThrow();
    }
}
