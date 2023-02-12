package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MidWeatherQueryServiceTest {

    private MidWeatherCloudRepository midWeatherCloudRepository;
    private MidWeatherRainRepository midWeatherRainRepository;
    private MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private MidWeatherQueryService midWeatherQueryService;

    private RegionCode regionCodeLand;
    private RegionCode regionCodeTa;
    private MidWeatherRain midWeatherRain;
    private MidWeatherCloud midWeatherCloud;
    private MidWeatherTemperature midWeatherTemperature;

    @BeforeEach
    void setUp() {
        midWeatherCloudRepository = new InMemoryMidWeatherCloudRepository();
        midWeatherRainRepository = new InMemoryMidWeatherRainRepository();
        midWeatherTemperatureRepository = new InMemoryMidWeatherTemperatureRepository();
        RegionCodeRepository regionCodeRepository = new InMemoryRegionCodeRepository();

        midWeatherQueryService = new MidWeatherQueryService(midWeatherCloudRepository, midWeatherRainRepository, midWeatherTemperatureRepository, regionCodeRepository);

        regionCodeLand = RegionCode.builder()
                .id(1L)
                .type(MidType.LAND)
                .code("11B00000")
                .state("서울")
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

        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        midWeatherRain = MidWeatherRain.builder()
                .id(1L)
                .regionCodeId(regionCodeLand.getId())
                .inquiryDate(now)
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
        midWeatherRainRepository.save(midWeatherRain);

        midWeatherCloud = MidWeatherCloud.builder()
                .id(1L)
                .regionCodeId(regionCodeLand.getId())
                .inquiryDate(now)
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
        midWeatherCloudRepository.save(midWeatherCloud);

        midWeatherTemperature = MidWeatherTemperature.builder()
                .id(1L)
                .regionCodeId(regionCodeTa.getId())
                .inquiryDate(now)
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
        midWeatherTemperatureRepository.save(midWeatherTemperature);
    }

    @Test
    void 중기_날씨_조회() {
        List<MidWeatherResponse> midWeatherResponses = midWeatherQueryService.midWeatherList("서울", 37.56100277777777, 126.99964166666666);

        LocalDate nowDate = LocalDate.now();

        assertWeather(midWeatherResponses.get(0), getDateFormat(nowDate, 0), getDateDisplayName(nowDate, 0), 0, 1, "맑음", "맑음", 0, 1);
        assertWeather(midWeatherResponses.get(1), getDateFormat(nowDate, 1), getDateDisplayName(nowDate, 1), 2, 3, "구름많음", "구름많음", 2, 3);
        assertWeather(midWeatherResponses.get(2), getDateFormat(nowDate, 2), getDateDisplayName(nowDate, 2), 4, 5, "맑음", "맑음", 4, 5);
        assertWeather(midWeatherResponses.get(3), getDateFormat(nowDate, 3), getDateDisplayName(nowDate, 3), 6, 7, "구름많음", "구름많음", 6, 7);
        assertWeather(midWeatherResponses.get(4), getDateFormat(nowDate, 4), getDateDisplayName(nowDate, 4), 8, 9, "맑음", "맑음", 8, 9);
        assertWeather(midWeatherResponses.get(5), getDateFormat(nowDate, 5), getDateDisplayName(nowDate, 5), 10, 11, "구름많음", "구름많음", 10, 11);
        assertWeather(midWeatherResponses.get(6), getDateFormat(nowDate, 6), getDateDisplayName(nowDate, 6), 12, 13, "맑음", "맑음", 12, 13);
        assertWeather(midWeatherResponses.get(7), getDateFormat(nowDate, 7), getDateDisplayName(nowDate, 7), 14, 15, "구름많음", "구름많음", 14, 15);
    }

    private void assertWeather(MidWeatherResponse midWeatherResponse, String date, String day, int rainAm, int rainPm, String cloudAm, String cloudPm, int tempMin, int tempMax) {
        assertAll(
                () -> assertThat(midWeatherResponse.getDate()).isEqualTo(date),
                () -> assertThat(midWeatherResponse.getDay()).isEqualTo(day),
                () -> assertThat(midWeatherResponse.getRainAm()).isEqualTo(rainAm),
                () -> assertThat(midWeatherResponse.getRainPm()).isEqualTo(rainPm),
                () -> assertThat(midWeatherResponse.getCloudAm()).isEqualTo(cloudAm),
                () -> assertThat(midWeatherResponse.getCloudPm()).isEqualTo(cloudPm),
                () -> assertThat(midWeatherResponse.getTempMin()).isEqualTo(tempMin),
                () -> assertThat(midWeatherResponse.getTempMax()).isEqualTo(tempMax)
        );
    }

    private String getDateFormat(LocalDate nowDate, int addDay) {
        return nowDate.plusDays(addDay).format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    private String getDateDisplayName(LocalDate nowDate, int addDay) {
        if (addDay == 0) {
            return "오늘";
        } else if (addDay == 1) {
            return "내일";
        }

        return nowDate.plusDays(addDay).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREA);
    }
}
