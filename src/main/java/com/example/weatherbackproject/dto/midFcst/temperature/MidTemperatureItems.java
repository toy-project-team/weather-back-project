package com.example.weatherbackproject.dto.midFcst.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MidTemperatureItems {

    @JsonProperty("item")
    private List<MidTemperatureDto> item;
}
