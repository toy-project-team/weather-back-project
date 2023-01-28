package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.MidWeatherDistanceDto;
import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Slf4j
@Transactional(readOnly = true)
@Service
public class MidWeatherQueryService {

    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;
    private final MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherQueryService(MidWeatherCloudRepository midWeatherCloudRepository, MidWeatherRainRepository midWeatherRainRepository, MidWeatherTemperatureRepository midWeatherTemperatureRepository, RegionCodeRepository regionCodeRepository) {
        this.midWeatherCloudRepository = midWeatherCloudRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherTemperatureRepository = midWeatherTemperatureRepository;
        this.regionCodeRepository = regionCodeRepository;
    }

    public List<MidWeatherResponse> midWeatherList(String state, double latitude, double longitude) {
        LocalDate nowDate = LocalDate.now();
        String nowFormat = nowDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        RegionCode regionCodeLand = regionCodeRepository.findByTypeAndStateContaining(MidType.LAND, state).orElseThrow();

        MidWeatherRain midWeatherRain = midWeatherRainRepository.findByRegionCodeIdAndInquiryDate(regionCodeLand.getId(), nowFormat).orElseThrow();
        MidWeatherCloud midWeatherCloud = midWeatherCloudRepository.findByRegionCodeIdAndInquiryDate(regionCodeLand.getId(), nowFormat).orElseThrow();
        MidWeatherTemperature midWeatherTemperature = getShortDistanceMidWeatherDistanceDto(nowFormat, latitude, longitude);

        return Arrays.asList(
                toMidWeatherResponse(0, nowDate, "오늘", midWeatherRain.getRainFall0Am(), midWeatherRain.getRainFall0Pm(), midWeatherTemperature.getTemperature0Min(), midWeatherTemperature.getTemperature0Max(), midWeatherCloud.getCloud0Am(), midWeatherCloud.getCloud0Pm()),
                toMidWeatherResponse(1, nowDate.plusDays(1L), "내일", midWeatherRain.getRainFall1Am(), midWeatherRain.getRainFall1Pm(), midWeatherTemperature.getTemperature1Min(), midWeatherTemperature.getTemperature1Max(), midWeatherCloud.getCloud1Am(), midWeatherCloud.getCloud1Pm()),
                toMidWeatherResponse(2, nowDate.plusDays(2L), getDayKorean(nowDate.plusDays(2L)), midWeatherRain.getRainFall2Am(), midWeatherRain.getRainFall2Pm(), midWeatherTemperature.getTemperature2Min(), midWeatherTemperature.getTemperature2Max(), midWeatherCloud.getCloud2Am(), midWeatherCloud.getCloud2Pm()),
                toMidWeatherResponse(3, nowDate.plusDays(3L), getDayKorean(nowDate.plusDays(3L)), midWeatherRain.getRainFall3Am(), midWeatherRain.getRainFall3Pm(), midWeatherTemperature.getTemperature3Min(), midWeatherTemperature.getTemperature3Max(), midWeatherCloud.getCloud3Am(), midWeatherCloud.getCloud3Pm()),
                toMidWeatherResponse(4, nowDate.plusDays(4L), getDayKorean(nowDate.plusDays(4L)), midWeatherRain.getRainFall4Am(), midWeatherRain.getRainFall4Pm(), midWeatherTemperature.getTemperature4Min(), midWeatherTemperature.getTemperature4Max(), midWeatherCloud.getCloud4Am(), midWeatherCloud.getCloud4Pm()),
                toMidWeatherResponse(5, nowDate.plusDays(5L), getDayKorean(nowDate.plusDays(5L)), midWeatherRain.getRainFall5Am(), midWeatherRain.getRainFall5Pm(), midWeatherTemperature.getTemperature5Min(), midWeatherTemperature.getTemperature5Max(), midWeatherCloud.getCloud5Am(), midWeatherCloud.getCloud5Pm()),
                toMidWeatherResponse(6, nowDate.plusDays(6L), getDayKorean(nowDate.plusDays(6L)), midWeatherRain.getRainFall6Am(), midWeatherRain.getRainFall6Pm(), midWeatherTemperature.getTemperature6Min(), midWeatherTemperature.getTemperature6Max(), midWeatherCloud.getCloud6Am(), midWeatherCloud.getCloud6Pm()),
                toMidWeatherResponse(7, nowDate.plusDays(7L), getDayKorean(nowDate.plusDays(7L)), midWeatherRain.getRainFall7Am(), midWeatherRain.getRainFall7Pm(), midWeatherTemperature.getTemperature7Min(), midWeatherTemperature.getTemperature7Max(), midWeatherCloud.getCloud7Am(), midWeatherCloud.getCloud7Pm())
        );
    }

    private MidWeatherResponse toMidWeatherResponse(int orders, LocalDate date, String day, int rainAm, int rainPm, int tempMin, int tempMax, String cloudAm, String cloudPm) {
        return MidWeatherResponse.builder()
                .orders(orders)
                .date(date.format(DateTimeFormatter.BASIC_ISO_DATE))
                .day(day)
                .rainAm(rainAm)
                .rainPm(rainPm)
                .cloudAm(cloudAm)
                .cloudPm(cloudPm)
                .tempMin(tempMin)
                .tempMax(tempMax)
                .build();
    }

    private String getDayKorean(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREA);
    }

    private MidWeatherTemperature getShortDistanceMidWeatherDistanceDto(String date, double latitude, double longitude) {
        MidWeatherDistanceDto midWeatherDistanceDto = getShortDistanceMidWeatherTemperature(latitude, longitude);
        return midWeatherTemperatureRepository.findByRegionCodeIdAndInquiryDate(midWeatherDistanceDto.getCodeId(), date).orElseThrow();
    }

    private MidWeatherDistanceDto getShortDistanceMidWeatherTemperature(double latitude, double longitude) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.TEMP);
        return regionCodes.stream()
                .map(regionCode -> MidWeatherDistanceDto.builder()
                        .codeId(regionCode.getId())
                        .distance(calculateDistance(latitude, longitude, regionCode.getLatitude(), regionCode.getLongitude()))
                        .build())
                .sorted(Comparator.comparing(MidWeatherDistanceDto::getDistance))
                .limit(1).toList().get(0);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371;
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }
}
