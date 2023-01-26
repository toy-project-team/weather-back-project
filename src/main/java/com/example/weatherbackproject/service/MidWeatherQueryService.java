package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

    public List<MidWeatherResponse> midWeatherList(String state) {
        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        RegionCode regionCode = regionCodeRepository.findByTypeAndStateContaining(MidType.LAND, state).orElseThrow();

        MidWeatherRain midWeatherRain = midWeatherRainRepository.findByRegionCodeIdAndInquiryDate(regionCode.getId(), now).orElseThrow();
        MidWeatherCloud midWeatherCloud = midWeatherCloudRepository.findByRegionCodeIdAndInquiryDate(regionCode.getId(), now).orElseThrow();
        MidWeatherTemperature midWeatherTemperature = midWeatherTemperatureRepository.findByRegionCodeIdAndInquiryDate(regionCode.getId(), now).orElseThrow();

        return Arrays.asList(
                toMidWeatherResponse(1, midWeatherRain.getRainFall3Am(), midWeatherRain.getRainFall3Pm(), midWeatherTemperature.getTemperature3Min(), midWeatherTemperature.getTemperature3Max(), midWeatherCloud.getCloud3Am(), midWeatherCloud.getCloud3Pm()),
                toMidWeatherResponse(2, midWeatherRain.getRainFall4Am(), midWeatherRain.getRainFall4Pm(), midWeatherTemperature.getTemperature4Min(), midWeatherTemperature.getTemperature4Max(), midWeatherCloud.getCloud4Am(), midWeatherCloud.getCloud4Pm()),
                toMidWeatherResponse(3, midWeatherRain.getRainFall5Am(), midWeatherRain.getRainFall5Pm(), midWeatherTemperature.getTemperature5Min(), midWeatherTemperature.getTemperature5Max(), midWeatherCloud.getCloud5Am(), midWeatherCloud.getCloud5Pm()),
                toMidWeatherResponse(4, midWeatherRain.getRainFall6Am(), midWeatherRain.getRainFall6Pm(), midWeatherTemperature.getTemperature6Min(), midWeatherTemperature.getTemperature6Max(), midWeatherCloud.getCloud6Am(), midWeatherCloud.getCloud6Pm()),
                toMidWeatherResponse(5, midWeatherRain.getRainFall7Am(), midWeatherRain.getRainFall7Pm(), midWeatherTemperature.getTemperature7Min(), midWeatherTemperature.getTemperature7Max(), midWeatherCloud.getCloud7Am(), midWeatherCloud.getCloud7Pm())
        );
    }

    private MidWeatherResponse toMidWeatherResponse(int orders, int rainAm, int rainPm, int tempMin, int tempMax, String cloudAm, String cloudPm) {
        return MidWeatherResponse.builder()
                .orders(orders)
                .rainAm(rainAm)
                .rainPm(rainPm)
                .cloudAm(cloudAm)
                .cloudPm(cloudPm)
                .tempMin(tempMin)
                .tempMax(tempMax)
                .build();
    }
}
