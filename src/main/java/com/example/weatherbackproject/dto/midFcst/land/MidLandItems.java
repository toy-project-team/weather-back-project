package com.example.weatherbackproject.dto.midFcst.land;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MidLandItems {

    @JsonProperty("item")
    private List<MidLandDto> item;
}
