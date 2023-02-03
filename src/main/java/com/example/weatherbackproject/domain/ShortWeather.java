package com.example.weatherbackproject.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShortWeather extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCodeId;
    private LocalDateTime inquiryDate;
    private int rainProbability;    // 강수 확률
    private String precipitationForm;  // 강수 형태
    private String rainPrecipitation; // 강수량
    private String snowAmount;        // 적설량
    private String cloudState;        // 구름양
    private int hourTemperature;   // 기온
    private int humidity;          // 습도

    @Builder
    public ShortWeather(Long regionCodeId, LocalDateTime inquiryDate, int rainProbability, String precipitationForm, String rainPrecipitation, String snowAmount, String cloudState, int hourTemperature, int humidity) {
        this.regionCodeId = regionCodeId;
        this.inquiryDate = inquiryDate;
        this.rainProbability = rainProbability;
        this.precipitationForm = precipitationForm;
        this.rainPrecipitation = rainPrecipitation;
        this.snowAmount = snowAmount;
        this.cloudState = cloudState;
        this.hourTemperature = hourTemperature;
        this.humidity = humidity;
    }

    public void updateWeather(int rainProbability, String precipitationForm, String rainPrecipitation, String snowAmount, String cloudState, int hourTemperature, int humidity) {
        this.rainProbability = rainProbability;
        this.precipitationForm = precipitationForm;
        this.rainPrecipitation = rainPrecipitation;
        this.snowAmount = snowAmount;
        this.cloudState = cloudState;
        this.hourTemperature = hourTemperature;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "ShortWeather{" +
                ", regionCodeId=" + regionCodeId +
                ", inquiryDate='" + inquiryDate + '\'' +
                ", rainProbability=" + rainProbability +
                ", precipitationForm='" + precipitationForm + '\'' +
                ", rainPrecipitation=" + rainPrecipitation +
                ", snowAmount=" + snowAmount +
                ", cloudState='" + cloudState + '\'' +
                ", hourTemperature=" + hourTemperature +
                ", humidity=" + humidity +
                '}';
    }
}
