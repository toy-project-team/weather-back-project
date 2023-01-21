package com.example.weatherbackproject.dto.midFcst.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MidTemperatureResponse {

    @JsonProperty("body")
    private MidTemperatureBody body;
}
