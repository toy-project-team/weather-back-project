package com.example.weatherbackproject.dto.midFcst.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
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
        @JsonProperty("taMax7") int taMax7,
        @JsonProperty("taMin8") int taMin8,
        @JsonProperty("taMax8") int taMax8
) {
}
