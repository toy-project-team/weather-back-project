package com.example.weatherbackproject.dto.shortFcst.vilage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShortVilageResponse {

    @JsonProperty("body")
    private ShortVilageBody body;
}
