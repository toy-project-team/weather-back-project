package com.example.weatherbackproject.dto.midFcst;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MidWeatherResponse {

    private int orders;
    private String date;
    private String day;
    private int rainAm;
    private int rainPm;
    private int tempMin;
    private int tempMax;
    private String cloudAm;
    private String cloudPm;

    @Builder
    public MidWeatherResponse(int orders, String date, String day, int rainAm, int rainPm, int tempMin, int tempMax, String cloudAm, String cloudPm) {
        this.orders = orders;
        this.date = date;
        this.day = day;
        this.rainAm = rainAm;
        this.rainPm = rainPm;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.cloudAm = cloudAm;
        this.cloudPm = cloudPm;
    }
}
