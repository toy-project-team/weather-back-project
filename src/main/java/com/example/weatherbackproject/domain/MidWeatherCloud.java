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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MidWeatherCloud extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCodeId;
    private String inquiryDate;
    private String cloud0Am;
    private String cloud0Pm;
    private String cloud1Am;
    private String cloud1Pm;
    private String cloud2Am;
    private String cloud2Pm;
    private String cloud3Am;
    private String cloud3Pm;
    private String cloud4Am;
    private String cloud4Pm;
    private String cloud5Am;
    private String cloud5Pm;
    private String cloud6Am;
    private String cloud6Pm;
    private String cloud7Am;
    private String cloud7Pm;
    private String cloud8;

    @Builder
    public MidWeatherCloud(Long regionCodeId, String inquiryDate, String cloud0Am, String cloud0Pm, String cloud1Am, String cloud1Pm, String cloud2Am, String cloud2Pm, String cloud3Am, String cloud3Pm, String cloud4Am, String cloud4Pm, String cloud5Am, String cloud5Pm, String cloud6Am, String cloud6Pm, String cloud7Am, String cloud7Pm, String cloud8) {
        this.regionCodeId = regionCodeId;
        this.inquiryDate = inquiryDate;
        this.cloud0Am = cloud0Am;
        this.cloud0Pm = cloud0Pm;
        this.cloud1Am = cloud1Am;
        this.cloud1Pm = cloud1Pm;
        this.cloud2Am = cloud2Am;
        this.cloud2Pm = cloud2Pm;
        this.cloud3Am = cloud3Am;
        this.cloud3Pm = cloud3Pm;
        this.cloud4Am = cloud4Am;
        this.cloud4Pm = cloud4Pm;
        this.cloud5Am = cloud5Am;
        this.cloud5Pm = cloud5Pm;
        this.cloud6Am = cloud6Am;
        this.cloud6Pm = cloud6Pm;
        this.cloud7Am = cloud7Am;
        this.cloud7Pm = cloud7Pm;
        this.cloud8 = cloud8;
    }

    public void updateCloud(String cloud3Am, String cloud3Pm, String cloud4Am, String cloud4Pm, String cloud5Am, String cloud5Pm, String cloud6Am, String cloud6Pm, String cloud7Am, String cloud7Pm, String cloud8) {
        this.cloud3Am = cloud3Am;
        this.cloud3Pm = cloud3Pm;
        this.cloud4Am = cloud4Am;
        this.cloud4Pm = cloud4Pm;
        this.cloud5Am = cloud5Am;
        this.cloud5Pm = cloud5Pm;
        this.cloud6Am = cloud6Am;
        this.cloud6Pm = cloud6Pm;
        this.cloud7Am = cloud7Am;
        this.cloud7Pm = cloud7Pm;
        this.cloud8 = cloud8;
    }

    public boolean equalsCodeAndDate(Long codeId, String date) {
        return regionCodeId.equals(codeId) && inquiryDate.equals(date);
    }
}
