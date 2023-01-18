package com.example.weatherbackproject.dto.midFcst.land;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MidLandResultApiDto {

    @JsonProperty("response")
    private MidLandResponse commonResponse;
}
