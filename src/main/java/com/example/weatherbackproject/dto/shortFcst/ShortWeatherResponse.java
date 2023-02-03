package com.example.weatherbackproject.dto.shortFcst;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortWeatherResponse {

    private String day;
    private String time;

    private int rainProbability;        // 강수 확률
    private String precipitationForm;   // 강수 형태
    private String rainPrecipitation;   // 강수량
    private String snowAmount;          // 적설량
    private String cloudState;          // 구름양
    private int temperature;            // 기온
    private int humidity;               // 습도

    @Builder
    public ShortWeatherResponse(LocalDateTime date, int rainProbability, String precipitationForm, String rainPrecipitation, String snowAmount, String cloudState, int temperature, int humidity) {
        this.day = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
        this.time = LocalTime.of(date.getHour(), 0).format(DateTimeFormatter.ISO_LOCAL_TIME);
        this.rainProbability = rainProbability;
        this.precipitationForm = precipitationForm;
        this.rainPrecipitation = rainPrecipitation;
        this.snowAmount = snowAmount;
        this.cloudState = cloudState;
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
