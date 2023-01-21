package com.example.weatherbackproject.dto.midFcst.temperature;

import com.example.weatherbackproject.domain.MidWeatherTemperature;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MidTemperatureDto(
        @JsonProperty int taMin3,
        @JsonProperty int taMax3,
        @JsonProperty int taMin4,
        @JsonProperty int taMax4,
        @JsonProperty int taMin5,
        @JsonProperty int taMax5,
        @JsonProperty int taMin6,
        @JsonProperty int taMax6,
        @JsonProperty int taMin7,
        @JsonProperty int taMax7
) {


    public MidWeatherTemperature toMidWeatherTemperature() {
        return MidWeatherTemperature.builder()
                .temperature3Min(taMin3)
                .temperature3Max(taMax3)
                .temperature3Min(taMin4)
                .temperature3Max(taMax4)
                .temperature3Min(taMin5)
                .temperature3Max(taMax5)
                .temperature3Min(taMin6)
                .temperature3Max(taMax6)
                .temperature3Min(taMin7)
                .temperature3Max(taMax7)
                .build();
    }
}
