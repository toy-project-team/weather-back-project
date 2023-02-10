package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.infra.FakeMidLandFcstWeatherApiClient;
import com.example.weatherbackproject.infra.FakeMidTaWeatherApiClient;
import com.example.weatherbackproject.infra.MidWeatherUriBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MidWeatherCommandServiceTest {

    private MidWeatherCloudRepository midWeatherCloudRepository = new InMemoryMidWeatherCloudRepository();
    private MidWeatherRainRepository midWeatherRainRepository = new InMemoryMidWeatherRainRepository();
    private MidWeatherTemperatureRepository midWeatherTemperatureRepository = new InMemoryMidWeatherTemperatureRepository();
    private RegionCodeRepository regionCodeRepository = new InMemoryRegionCodeRepository();
    private MidWeatherCommandService midWeatherCommandService;

    private RegionCode regionCodeLand;
    private RegionCode regionCodeTa;
    private MidWeatherRain midWeatherRain;
    private MidWeatherCloud midWeatherCloud;
    private MidWeatherTemperature midWeatherTemperature;

    @BeforeEach
    void setUp() {
        midWeatherCommandService = new MidWeatherCommandService(new FakeMidLandFcstWeatherApiClient(), new FakeMidTaWeatherApiClient(), new MidWeatherUriBuilder(), midWeatherCloudRepository, midWeatherRainRepository,
                midWeatherTemperatureRepository, regionCodeRepository);

        regionCodeLand = RegionCode.builder()
                .id(1L)
                .type(MidType.LAND)
                .code("11B00000")
                .state("서울특별시")
                .latitude(0)
                .longitude(0)
                .build();
        regionCodeRepository.save(regionCodeLand);

        regionCodeTa = RegionCode.builder()
                .id(2L)
                .type(MidType.TEMP)
                .code("11B10101")
                .state("서울")
                .latitude(37.56356944444444)
                .longitude(126.98000833333333)
                .build();
        regionCodeRepository.save(regionCodeTa);

        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE);
        midWeatherRain = MidWeatherRain.builder()
                .id(1L)
                .regionCodeId(regionCodeLand.getId())
                .inquiryDate(yesterday)
                .rainFall0Am(0)
                .rainFall0Pm(1)
                .rainFall1Am(2)
                .rainFall1Pm(3)
                .rainFall2Am(4)
                .rainFall2Pm(5)
                .rainFall3Am(6)
                .rainFall3Pm(7)
                .rainFall4Am(8)
                .rainFall4Pm(9)
                .rainFall5Am(10)
                .rainFall5Pm(11)
                .rainFall6Am(12)
                .rainFall6Pm(13)
                .rainFall7Am(14)
                .rainFall7Pm(15)
                .rainFall8(16)
                .build();

        midWeatherCloud = MidWeatherCloud.builder()
                .id(1L)
                .regionCodeId(regionCodeLand.getId())
                .inquiryDate(yesterday)
                .cloud0Am("맑음")
                .cloud0Pm("맑음")
                .cloud1Am("구름많음")
                .cloud1Pm("구름많음")
                .cloud2Am("맑음")
                .cloud2Pm("맑음")
                .cloud3Am("구름많음")
                .cloud3Pm("구름많음")
                .cloud4Am("맑음")
                .cloud4Pm("맑음")
                .cloud5Am("구름많음")
                .cloud5Pm("구름많음")
                .cloud6Am("맑음")
                .cloud6Pm("맑음")
                .cloud7Am("구름많음")
                .cloud7Pm("구름많음")
                .cloud8("맑음")
                .build();

        midWeatherTemperature = MidWeatherTemperature.builder()
                .id(1L)
                .regionCodeId(regionCodeTa.getId())
                .inquiryDate(yesterday)
                .temperature0Min(0)
                .temperature0Max(1)
                .temperature1Min(2)
                .temperature1Max(3)
                .temperature2Min(4)
                .temperature2Max(5)
                .temperature3Min(6)
                .temperature3Max(7)
                .temperature4Min(8)
                .temperature4Max(9)
                .temperature5Min(10)
                .temperature5Max(11)
                .temperature6Min(12)
                .temperature6Max(13)
                .temperature7Min(14)
                .temperature7Max(15)
                .temperature8Min(16)
                .temperature8Max(17)
                .build();
    }

    @Test
    void 중기_날씨_LandFcst_생성_강수량() {
        midWeatherRainRepository.save(midWeatherRain);

        midWeatherCommandService.createMidLandFcst();

        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        MidWeatherRain midWeatherRain1 = midWeatherRainRepository.findAllByInquiryDate(now).get(0);

        assertAll(
                () -> assertThat(midWeatherRain1.getRainFall0Am()).isEqualTo(2),
                () -> assertThat(midWeatherRain1.getRainFall0Pm()).isEqualTo(3),
                () -> assertThat(midWeatherRain1.getRainFall1Am()).isEqualTo(4),
                () -> assertThat(midWeatherRain1.getRainFall1Pm()).isEqualTo(5),
                () -> assertThat(midWeatherRain1.getRainFall7Am()).isEqualTo(16),
                () -> assertThat(midWeatherRain1.getRainFall7Pm()).isEqualTo(16),
                () -> assertThat(midWeatherRain1.getRainFall8()).isEqualTo(0)
        );
    }

    @Test
    void 중기_날씨_LandFcst_생성_구름양() {
        midWeatherCloudRepository.save(midWeatherCloud);

        midWeatherCommandService.createMidLandFcst();

        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        MidWeatherCloud midWeatherCloud1 = midWeatherCloudRepository.findAllByInquiryDate(now).get(0);

        assertAll(
                () -> assertThat(midWeatherCloud1.getCloud0Am()).isEqualTo("구름많음"),
                () -> assertThat(midWeatherCloud1.getCloud0Pm()).isEqualTo("구름많음"),
                () -> assertThat(midWeatherCloud1.getCloud1Am()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud1Pm()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud7Am()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud7Pm()).isEqualTo("맑음")
        );
    }

    @Test
    void 중기_날씨_Ta_생성_기온() {
        midWeatherTemperatureRepository.save(midWeatherTemperature);

        midWeatherCommandService.createMidTa();

        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        MidWeatherTemperature midWeatherTemperature1 = midWeatherTemperatureRepository.findAllByInquiryDate(now).get(0);

        assertAll(
                () -> assertThat(midWeatherTemperature1.getTemperature0Min()).isEqualTo(2),
                () -> assertThat(midWeatherTemperature1.getTemperature0Max()).isEqualTo(3),
                () -> assertThat(midWeatherTemperature1.getTemperature1Min()).isEqualTo(4),
                () -> assertThat(midWeatherTemperature1.getTemperature1Max()).isEqualTo(5),
                () -> assertThat(midWeatherTemperature1.getTemperature7Min()).isEqualTo(16),
                () -> assertThat(midWeatherTemperature1.getTemperature7Max()).isEqualTo(17)
        );
    }

    @Test
    void 중기_날씨_LandFcst_api_데이터_수정() {
        midWeatherRainRepository.save(midWeatherRain);
        midWeatherCloudRepository.save(midWeatherCloud);

        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        midWeatherCommandService.createMidLandFcst();
        midWeatherCommandService.updateMidLandFcst(now, "0600");

        MidWeatherRain midWeatherRain1 = midWeatherRainRepository.findAllByInquiryDate(now).get(0);
        MidWeatherCloud midWeatherCloud1 = midWeatherCloudRepository.findAllByInquiryDate(now).get(0);

        assertAll(
                () -> assertThat(midWeatherRain1.getRainFall3Am()).isEqualTo(11),
                () -> assertThat(midWeatherRain1.getRainFall3Pm()).isEqualTo(12),
                () -> assertThat(midWeatherRain1.getRainFall4Am()).isEqualTo(13),
                () -> assertThat(midWeatherRain1.getRainFall4Pm()).isEqualTo(14),
                () -> assertThat(midWeatherRain1.getRainFall7Am()).isEqualTo(19),
                () -> assertThat(midWeatherRain1.getRainFall7Pm()).isEqualTo(20),
                () -> assertThat(midWeatherRain1.getRainFall8()).isEqualTo(21),
                () -> assertThat(midWeatherCloud1.getCloud3Am()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud3Pm()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud4Am()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud4Pm()).isEqualTo("맑음"),
                () -> assertThat(midWeatherCloud1.getCloud7Am()).isEqualTo("흐림"),
                () -> assertThat(midWeatherCloud1.getCloud7Pm()).isEqualTo("흐림")
        );
    }

    @Test
    void 중기_날씨_Ta_api_데이터_수정() {
        midWeatherTemperatureRepository.save(midWeatherTemperature);

        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        midWeatherCommandService.createMidTa();
        midWeatherCommandService.updateMidTa(now, "0600");

        MidWeatherTemperature midWeatherTemperature1 = midWeatherTemperatureRepository.findAllByInquiryDate(now).get(0);

        assertAll(
                () -> assertThat(midWeatherTemperature1.getTemperature3Min()).isEqualTo(5),
                () -> assertThat(midWeatherTemperature1.getTemperature3Max()).isEqualTo(5),
                () -> assertThat(midWeatherTemperature1.getTemperature4Min()).isEqualTo(5),
                () -> assertThat(midWeatherTemperature1.getTemperature4Max()).isEqualTo(6),
                () -> assertThat(midWeatherTemperature1.getTemperature7Min()).isEqualTo(7),
                () -> assertThat(midWeatherTemperature1.getTemperature7Max()).isEqualTo(8)
        );
    }
}
