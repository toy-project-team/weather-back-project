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
public class MidWeatherRain extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionCodeId;
    private String inquiryDate;
    private int rainFall0Am;
    private int rainFall0Pm;
    private int rainFall1Am;
    private int rainFall1Pm;
    private int rainFall2Am;
    private int rainFall2Pm;
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
    private int rainFall8;

    @Builder
    public MidWeatherRain(Long regionCodeId, String inquiryDate, int rainFall0Am, int rainFall0Pm, int rainFall1Am, int rainFall1Pm, int rainFall2Am, int rainFall2Pm, int rainFall3Am, int rainFall3Pm, int rainFall4Am, int rainFall4Pm, int rainFall5Am, int rainFall5Pm, int rainFall6Am, int rainFall6Pm, int rainFall7Am, int rainFall7Pm, int rainFall8) {
        this.regionCodeId = regionCodeId;
        this.inquiryDate = inquiryDate;
        this.rainFall0Am = rainFall0Am;
        this.rainFall0Pm = rainFall0Pm;
        this.rainFall1Am = rainFall1Am;
        this.rainFall1Pm = rainFall1Pm;
        this.rainFall2Am = rainFall2Am;
        this.rainFall2Pm = rainFall2Pm;
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
        this.rainFall8 = rainFall8;
    }

    @Builder
    public MidWeatherRain(Long id, Long regionCodeId, String inquiryDate, int rainFall0Am, int rainFall0Pm, int rainFall1Am, int rainFall1Pm, int rainFall2Am, int rainFall2Pm, int rainFall3Am, int rainFall3Pm, int rainFall4Am, int rainFall4Pm, int rainFall5Am, int rainFall5Pm, int rainFall6Am, int rainFall6Pm, int rainFall7Am, int rainFall7Pm, int rainFall8) {
        this(regionCodeId, inquiryDate, rainFall0Am, rainFall0Pm, rainFall1Am, rainFall1Pm, rainFall2Am, rainFall2Pm, rainFall3Am, rainFall3Pm, rainFall4Am, rainFall4Pm, rainFall5Am, rainFall5Pm, rainFall6Am, rainFall6Pm, rainFall7Am, rainFall7Pm, rainFall8);
        this.id = id;
    }

    public void updateRain(int rainFall3Am, int rainFall3Pm, int rainFall4Am, int rainFall4Pm, int rainFall5Am, int rainFall5Pm, int rainFall6Am, int rainFall6Pm, int rainFall7Am, int rainFall7Pm, int rainFall8) {
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
        this.rainFall8 = rainFall8;
    }

    public boolean equalsCodeAndDate(Long codeId, String date) {
        return regionCodeId.equals(codeId) && inquiryDate.equals(date);
    }

    public MidWeatherRain nextMidWeatherRain(String nowDate, MidWeatherRain midWeatherRain) {
        return MidWeatherRain.builder()
                .regionCodeId(midWeatherRain.getRegionCodeId())
                .inquiryDate(nowDate)
                .rainFall0Am(midWeatherRain.getRainFall1Am())
                .rainFall0Pm(midWeatherRain.getRainFall1Pm())
                .rainFall1Am(midWeatherRain.getRainFall2Am())
                .rainFall1Pm(midWeatherRain.getRainFall2Pm())
                .rainFall2Am(midWeatherRain.getRainFall3Am())
                .rainFall2Pm(midWeatherRain.getRainFall3Pm())
                .rainFall3Am(midWeatherRain.getRainFall4Am())
                .rainFall3Pm(midWeatherRain.getRainFall4Pm())
                .rainFall4Am(midWeatherRain.getRainFall5Am())
                .rainFall4Pm(midWeatherRain.getRainFall5Pm())
                .rainFall5Am(midWeatherRain.getRainFall6Am())
                .rainFall5Pm(midWeatherRain.getRainFall6Pm())
                .rainFall6Am(midWeatherRain.getRainFall7Am())
                .rainFall6Pm(midWeatherRain.getRainFall7Pm())
                .rainFall7Am(midWeatherRain.getRainFall8())
                .rainFall7Pm(midWeatherRain.getRainFall8())
                .build();
    }
}
