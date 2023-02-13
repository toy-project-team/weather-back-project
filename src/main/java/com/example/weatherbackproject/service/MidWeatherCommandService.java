package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;
import com.example.weatherbackproject.infra.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Transactional
@Service
public class MidWeatherCommandService {

    private final WeatherApiClient<MidLandDto> midLandFcstWeatherApiClient;
    private final WeatherApiClient<MidTemperatureDto> midTaWeatherApiClient;
    private final MidWeatherUriBuilder midWeatherUriBuilderService;
    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;
    private final MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherCommandService(@Qualifier("midLandFcstWeatherApiClient") WeatherApiClient<MidLandDto> midLandFcstWeatherApiClient,
                                    @Qualifier("midTaWeatherApiClient") WeatherApiClient<MidTemperatureDto> midTaWeatherApiClient, MidWeatherUriBuilder midWeatherUriBuilderService,
                                    MidWeatherCloudRepository midWeatherCloudRepository, MidWeatherRainRepository midWeatherRainRepository, MidWeatherTemperatureRepository midWeatherTemperatureRepository,
                                    RegionCodeRepository regionCodeRepository) {
        this.midLandFcstWeatherApiClient = midLandFcstWeatherApiClient;
        this.midTaWeatherApiClient = midTaWeatherApiClient;
        this.midWeatherUriBuilderService = midWeatherUriBuilderService;
        this.midWeatherCloudRepository = midWeatherCloudRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherTemperatureRepository = midWeatherTemperatureRepository;
        this.regionCodeRepository = regionCodeRepository;
    }

    public void createMidLandFcst() {
        LocalDate now = LocalDate.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        String beforeDate = now.minusDays(1L).format(DateTimeFormatter.BASIC_ISO_DATE);

        List<MidWeatherRain> beforeMidWeatherRains = midWeatherRainRepository.findAllByInquiryDate(beforeDate);
        List<MidWeatherCloud> beforeMidWeatherCloud = midWeatherCloudRepository.findAllByInquiryDate(beforeDate);

        for (MidWeatherRain midWeatherRain : beforeMidWeatherRains) {
            midWeatherRainRepository.save(midWeatherRain.nextMidWeatherRain(nowDate, midWeatherRain));
        }

        for (MidWeatherCloud midWeatherCloud : beforeMidWeatherCloud) {
            midWeatherCloudRepository.save(midWeatherCloud.nextMidWeatherCloud(nowDate, midWeatherCloud));
        }
    }

    public void createMidTa() {
        LocalDate now = LocalDate.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        String beforeDate = now.minusDays(1L).format(DateTimeFormatter.BASIC_ISO_DATE);

        List<MidWeatherTemperature> midWeatherTemperatures = midWeatherTemperatureRepository.findAllByInquiryDate(beforeDate);

        for (MidWeatherTemperature midWeatherTemperature : midWeatherTemperatures) {
            midWeatherTemperatureRepository.save(midWeatherTemperature.nextMidWeatherTemperature(nowDate, midWeatherTemperature));
        }
    }

    public void updateMidLandFcst(String date, String time) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.LAND);
        MidWeatherRains midWeatherRains = new MidWeatherRains(midWeatherRainRepository.findAllByInquiryDate(date));
        MidWeatherClouds midWeatherClouds = new MidWeatherClouds(midWeatherCloudRepository.findAllByInquiryDate(date));

        for (RegionCode code : regionCodes) {
            try {
                URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + time);
                MidLandDto midLandDto = midLandFcstWeatherApiClient.requestWeather(uri);

                MidWeatherRain midWeatherRain = midWeatherRains.findMidWeatherRain(code.getId(), date);
                midWeatherRain.updateRain(midLandDto.rnSt3Am(), midLandDto.rnSt3Pm(), midLandDto.rnSt4Am(), midLandDto.rnSt4Pm(), midLandDto.rnSt5Am(), midLandDto.rnSt5Pm(), midLandDto.rnSt6Am(), midLandDto.rnSt6Pm(), midLandDto.rnSt7Am(), midLandDto.rnSt7Pm(), midLandDto.rnSt8());

                MidWeatherCloud midWeatherCloud = midWeatherClouds.findMidWeatherCloud(code.getId(), date);
                midWeatherCloud.updateCloud(midLandDto.wf3Am(), midLandDto.wf3Pm(), midLandDto.wf4Am(), midLandDto.wf4Pm(), midLandDto.wf5Am(), midLandDto.wf5Pm(), midLandDto.wf6Am(), midLandDto.wf6Pm(), midLandDto.wf7Am(), midLandDto.wf7Pm(), midLandDto.wf8());
            } catch (Exception e) {
                log.error("[MidWeatherCommandService.updateMidLandFcst] date : {}, time : {}, code : {}, 도시 : {}", date, time, code.getCode(), code.getState());
            }
        }
    }

    public void updateMidTa(String date, String time) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.TEMP);
        MidWeatherTemperatures midWeatherTemperatures = new MidWeatherTemperatures(midWeatherTemperatureRepository.findAllByInquiryDate(date));

        for (RegionCode code : regionCodes) {
            try {
                URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date + time);
                MidTemperatureDto midTemperatureDto = midTaWeatherApiClient.requestWeather(uri);

                MidWeatherTemperature midWeatherTemperature = midWeatherTemperatures.findMidWeatherTemperatures(code.getId(), date);
                midWeatherTemperature.updateTemperature(midTemperatureDto.taMin3(), midTemperatureDto.taMax3(), midTemperatureDto.taMin4(), midTemperatureDto.taMax4(), midTemperatureDto.taMin5(), midTemperatureDto.taMax5(), midTemperatureDto.taMin6(), midTemperatureDto.taMax6(), midTemperatureDto.taMin7(), midTemperatureDto.taMax7(), midTemperatureDto.taMin8(), midTemperatureDto.taMax8());
            } catch (Exception e) {
                log.error("[MidWeatherCommandService.updateMidTa] date : {}, time : {}, code : {}, 도시 : {}", date, time, code.getCode(), code.getState());
            }
        }
    }
}
