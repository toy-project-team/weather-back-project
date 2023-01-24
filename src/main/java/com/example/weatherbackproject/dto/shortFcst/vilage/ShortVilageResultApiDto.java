package com.example.weatherbackproject.dto.shortFcst.vilage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShortVilageResultApiDto {

    @JsonProperty("response")
    private ShortVilageResponse commonResponse;
}
