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

    @Builder
    public MidWeatherCloud(Long regionCodeId, String inquiryDate, String cloud3Am, String cloud3Pm, String cloud4Am, String cloud4Pm, String cloud5Am, String cloud5Pm, String cloud6Am, String cloud6Pm, String cloud7Am, String cloud7Pm) {
        this.regionCodeId = regionCodeId;
        this.inquiryDate = inquiryDate;
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
    }

    public void updateCloud(String cloud3Am, String cloud3Pm, String cloud4Am, String cloud4Pm, String cloud5Am, String cloud5Pm, String cloud6Am, String cloud6Pm, String cloud7Am, String cloud7Pm) {
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
    }

    public boolean equalsCodeAndDate(Long codeId, String date) {
        return regionCodeId.equals(codeId) && inquiryDate.equals(date);
    }
}
