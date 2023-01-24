package com.example.weatherbackproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShortWeather extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCodeId;
    private String inquiryDate;
    private String baseTime;
    private int rainProbability;
    private int rainPrecipitation;
    private int snowAmount;
    private int cloudState;
    private int hourTemperature;
}
