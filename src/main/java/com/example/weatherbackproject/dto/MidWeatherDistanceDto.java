package com.example.weatherbackproject.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MidWeatherDistanceDto {

    private Long codeId;
    private double distance;

    @Builder
    public MidWeatherDistanceDto(Long codeId, double distance) {
        this.codeId = codeId;
        this.distance = distance;
    }
}
