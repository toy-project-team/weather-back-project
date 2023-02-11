package com.example.weatherbackproject.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MidWeatherTemperature extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCodeId;
    private String inquiryDate;
    private int temperature0Min;
    private int temperature0Max;
    private int temperature1Min;
    private int temperature1Max;
    private int temperature2Min;
    private int temperature2Max;
    private int temperature3Min;
    private int temperature3Max;
    private int temperature4Min;
    private int temperature4Max;
    private int temperature5Min;
    private int temperature5Max;
    private int temperature6Min;
    private int temperature6Max;
    private int temperature7Min;
    private int temperature7Max;
    private int temperature8Min;
    private int temperature8Max;

    @Builder
    public MidWeatherTemperature(Long regionCodeId, String inquiryDate, int temperature0Min, int temperature0Max, int temperature1Min, int temperature1Max, int temperature2Min, int temperature2Max, int temperature3Min, int temperature3Max, int temperature4Min, int temperature4Max, int temperature5Min, int temperature5Max, int temperature6Min, int temperature6Max, int temperature7Min, int temperature7Max, int temperature8Min, int temperature8Max) {
        this.regionCodeId = regionCodeId;
        this.inquiryDate = inquiryDate;
        this.temperature0Min = temperature0Min;
        this.temperature0Max = temperature0Max;
        this.temperature1Min = temperature1Min;
        this.temperature1Max = temperature1Max;
        this.temperature2Min = temperature2Min;
        this.temperature2Max = temperature2Max;
        this.temperature3Min = temperature3Min;
        this.temperature3Max = temperature3Max;
        this.temperature4Min = temperature4Min;
        this.temperature4Max = temperature4Max;
        this.temperature5Min = temperature5Min;
        this.temperature5Max = temperature5Max;
        this.temperature6Min = temperature6Min;
        this.temperature6Max = temperature6Max;
        this.temperature7Min = temperature7Min;
        this.temperature7Max = temperature7Max;
        this.temperature8Min = temperature8Min;
        this.temperature8Max = temperature8Max;
    }

    @Builder
    public MidWeatherTemperature(Long id, Long regionCodeId, String inquiryDate, int temperature0Min, int temperature0Max, int temperature1Min, int temperature1Max, int temperature2Min, int temperature2Max, int temperature3Min, int temperature3Max, int temperature4Min, int temperature4Max, int temperature5Min, int temperature5Max, int temperature6Min, int temperature6Max, int temperature7Min, int temperature7Max, int temperature8Min, int temperature8Max) {
        this(regionCodeId, inquiryDate, temperature0Min, temperature0Max, temperature1Min, temperature1Max, temperature2Min, temperature2Max, temperature3Min, temperature3Max, temperature4Min, temperature4Max, temperature5Min, temperature5Max, temperature6Min, temperature6Max, temperature7Min, temperature7Max, temperature8Min, temperature8Max);
        this.id = id;
    }

    public void updateTemperature(int temperature3Min, int temperature3Max, int temperature4Min, int temperature4Max, int temperature5Min, int temperature5Max, int temperature6Min, int temperature6Max, int temperature7Min, int temperature7Max, int temperature8Min, int temperature8Max) {
        this.temperature3Min = temperature3Min;
        this.temperature3Max = temperature3Max;
        this.temperature4Min = temperature4Min;
        this.temperature4Max = temperature4Max;
        this.temperature5Min = temperature5Min;
        this.temperature5Max = temperature5Max;
        this.temperature6Min = temperature6Min;
        this.temperature6Max = temperature6Max;
        this.temperature7Min = temperature7Min;
        this.temperature7Max = temperature7Max;
        this.temperature8Min = temperature8Min;
        this.temperature8Max = temperature8Max;
    }

    public boolean equalsCodeAndDate(Long codeId, String date) {
        return regionCodeId.equals(codeId) && inquiryDate.equals(date);
    }

    public MidWeatherTemperature nextMidWeatherTemperature(String nowDate, MidWeatherTemperature midWeatherTemperature) {
        return MidWeatherTemperature.builder()
                .regionCodeId(midWeatherTemperature.getRegionCodeId())
                .inquiryDate(nowDate)
                .temperature0Min(midWeatherTemperature.getTemperature1Min())
                .temperature0Max(midWeatherTemperature.getTemperature1Max())
                .temperature1Min(midWeatherTemperature.getTemperature2Min())
                .temperature1Max(midWeatherTemperature.getTemperature2Max())
                .temperature2Min(midWeatherTemperature.getTemperature3Min())
                .temperature2Max(midWeatherTemperature.getTemperature3Max())
                .temperature3Min(midWeatherTemperature.getTemperature4Min())
                .temperature3Max(midWeatherTemperature.getTemperature4Max())
                .temperature4Min(midWeatherTemperature.getTemperature5Min())
                .temperature4Max(midWeatherTemperature.getTemperature5Max())
                .temperature5Min(midWeatherTemperature.getTemperature6Min())
                .temperature5Max(midWeatherTemperature.getTemperature6Max())
                .temperature6Min(midWeatherTemperature.getTemperature7Min())
                .temperature6Max(midWeatherTemperature.getTemperature7Max())
                .temperature7Min(midWeatherTemperature.getTemperature8Min())
                .temperature7Max(midWeatherTemperature.getTemperature8Max())
                .build();
    }
}
