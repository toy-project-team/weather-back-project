package com.example.weatherbackproject.dto.midFcst.temperature;

import com.example.weatherbackproject.domain.MidWeatherTemperature;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MidTemperatureDto(
        @JsonProperty("taMin3") int taMin3,
        @JsonProperty("taMax3") int taMax3,
        @JsonProperty("taMin4") int taMin4,
        @JsonProperty("taMax4") int taMax4,
        @JsonProperty("taMin5") int taMin5,
        @JsonProperty("taMax5") int taMax5,
        @JsonProperty("taMin6") int taMin6,
        @JsonProperty("taMax6") int taMax6,
        @JsonProperty("taMin7") int taMin7,
        @JsonProperty("taMax7") int taMax7
) {


    public MidWeatherTemperature toMidWeatherTemperature(Long codeId, String date) {
        return MidWeatherTemperature.builder()
                .regionCodeId(codeId)
                .inquiryDate(date)
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
