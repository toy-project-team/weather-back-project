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
public class MidWeatherRain extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCodeId;
    private String inquiryDate;
    private int rainFall3Am;
    private int rainFall3Pm;
    private int rainFall4Am;
    private int rainFall4Pm;
    private int rainFall5Am;
    private int rainFall5Pm;
    private int rainFall6Am;
    private int rainFall6Pm;
    private int rainFall7Am;
    private int rainFall7Pm;

    @Builder
    public MidWeatherRain(Long regionCodeId, String inquiryDate, int rainFall3Am, int rainFall3Pm, int rainFall4Am, int rainFall4Pm, int rainFall5Am, int rainFall5Pm, int rainFall6Am, int rainFall6Pm, int rainFall7Am, int rainFall7Pm) {
        this.regionCodeId = regionCodeId;
        this.inquiryDate = inquiryDate;
        this.rainFall3Am = rainFall3Am;
        this.rainFall3Pm = rainFall3Pm;
        this.rainFall4Am = rainFall4Am;
        this.rainFall4Pm = rainFall4Pm;
        this.rainFall5Am = rainFall5Am;
        this.rainFall5Pm = rainFall5Pm;
        this.rainFall6Am = rainFall6Am;
        this.rainFall6Pm = rainFall6Pm;
        this.rainFall7Am = rainFall7Am;
        this.rainFall7Pm = rainFall7Pm;
    }

    public void updateRain(int rainFall3Am, int rainFall3Pm, int rainFall4Am, int rainFall4Pm, int rainFall5Am, int rainFall5Pm, int rainFall6Am, int rainFall6Pm, int rainFall7Am, int rainFall7Pm) {
        this.rainFall3Am = rainFall3Am;
        this.rainFall3Pm = rainFall3Pm;
        this.rainFall4Am = rainFall4Am;
        this.rainFall4Pm = rainFall4Pm;
        this.rainFall5Am = rainFall5Am;
        this.rainFall5Pm = rainFall5Pm;
        this.rainFall6Am = rainFall6Am;
        this.rainFall6Pm = rainFall6Pm;
        this.rainFall7Am = rainFall7Am;
        this.rainFall7Pm = rainFall7Pm;
    }

    public boolean equalsCodeAndDate(Long codeId, String date) {
        return regionCodeId.equals(codeId) && inquiryDate.equals(date);
    }
}
