package com.example.weatherbackproject.dto.midFcst.land;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MidLandBody {

    @JsonProperty("items")
    private MidLandItems items;
}
