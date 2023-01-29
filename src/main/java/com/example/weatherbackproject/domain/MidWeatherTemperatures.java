package com.example.weatherbackproject.domain;

import java.util.ArrayList;
import java.util.List;

public class MidWeatherTemperatures {

    private List<MidWeatherTemperature> midWeatherTemperatures = new ArrayList<>();

    public MidWeatherTemperatures(List<MidWeatherTemperature> midWeatherTemperatures) {
        this.midWeatherTemperatures = midWeatherTemperatures;
    }

    public MidWeatherTemperature findMidWeatherTemperatures(Long codeId, String date) {
        return midWeatherTemperatures.stream()
                .filter(tem -> tem.equalsCodeAndDate(codeId, date))
                .findFirst()
                .orElseThrow();
    }
}
