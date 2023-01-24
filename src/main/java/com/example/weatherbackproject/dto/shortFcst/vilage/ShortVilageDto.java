package com.example.weatherbackproject.dto.shortFcst.vilage;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortVilageDto(
        @JsonProperty("baseDate") String baseDate,
        @JsonProperty("baseTime") String baseTime,
        @JsonProperty("category") String category,
        @JsonProperty("fcstDate") String fcstDate,
        @JsonProperty("fcstTime") String fcstTime,
        @JsonProperty("fcstValue") String fcstValue,
        @JsonProperty("nx") double nx,
        @JsonProperty("ny") double ny
) {

    @Override
    public String toString() {
        return "ShortVilageDto{" +
                "baseDate='" + baseDate + '\'' +
                ", baseTime='" + baseTime + '\'' +
                ", category='" + category + '\'' +
                ", fcstDate=" + fcstDate +
                ", fcstTime=" + fcstTime +
                ", fcstValue=" + fcstValue +
                ", nx=" + nx +
                ", ny=" + ny +
                '}';
    }
}
