package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;
import com.example.weatherbackproject.infra.MidWeatherApiClient;
import com.example.weatherbackproject.infra.MidWeatherUriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Slf4j
@Transactional
@Service
public class MidWeatherCommandService {

    private final MidWeatherApiClient midWeatherApiService;
    private final MidWeatherUriBuilder midWeatherUriBuilderService;
    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;
    private final MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherCommandService(MidWeatherApiClient midWeatherApiService, MidWeatherUriBuilder midWeatherUriBuilderService, MidWeatherCloudRepository midWeatherCloudRepository, MidWeatherRainRepository midWeatherRainRepository, MidWeatherTemperatureRepository midWeatherTemperatureRepository, RegionCodeRepository regionCodeRepository) {
        this.midWeatherApiService = midWeatherApiService;
        this.midWeatherUriBuilderService = midWeatherUriBuilderService;
        this.midWeatherCloudRepository = midWeatherCloudRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherTemperatureRepository = midWeatherTemperatureRepository;
        this.regionCodeRepository = regionCodeRepository;
    }

    public void createMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.LAND);
        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + "0600");

            MidLandDto midLandDto = midWeatherApiService.requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midLandDto.toMidWeatherRain(code.getId(), date);
            MidWeatherCloud midWeatherCloud = midLandDto.toMidWeatherCloud(code.getId(), date);

            midWeatherRainRepository.save(midWeatherRain);
            midWeatherCloudRepository.save(midWeatherCloud);
        }
    }

    public void updateMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.LAND);
        List<MidWeatherRain> midWeatherRains = midWeatherRainRepository.findAllByInquiryDate(date);
        List<MidWeatherCloud> midWeatherClouds = midWeatherCloudRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + "1800");

            MidLandDto midLandDto = midWeatherApiService.requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midWeatherRains.stream()
                    .filter(rain -> rain.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherRain.updateRain(midLandDto.rnSt4Am(), midLandDto.rnSt4Pm(), midLandDto.rnSt5Am(), midLandDto.rnSt5Pm(), midLandDto.rnSt6Am(), midLandDto.rnSt6Pm(), midLandDto.rnSt7Am(), midLandDto.rnSt7Pm());

            MidWeatherCloud midWeatherCloud = midWeatherClouds.stream()
                    .filter(cloud -> cloud.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherCloud.updateCloud(midLandDto.wf3Am(), midLandDto.wf3Pm(), midLandDto.wf4Am(), midLandDto.wf4Pm(), midLandDto.wf5Am(), midLandDto.wf5Pm(), midLandDto.wf6Am(), midLandDto.wf6Pm(), midLandDto.wf7Am(), midLandDto.wf7Pm());
        }
    }

    public void createMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.TEMP);
        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date + "0600");

            MidWeatherTemperature midWeatherTemperature = midWeatherApiService.requestMidTa(uri).toMidWeatherTemperature(code.getId(), date);

            midWeatherTemperatureRepository.save(midWeatherTemperature);
        }
    }

    public void updateMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.TEMP);
        List<MidWeatherTemperature> midWeatherTemperatures = midWeatherTemperatureRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date + "1800");

            MidTemperatureDto midTemperatureDto = midWeatherApiService.requestMidTa(uri);

            MidWeatherTemperature midWeatherTemperature = midWeatherTemperatures.stream()
                    .filter(tem -> tem.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherTemperature.updateTemperature(midTemperatureDto.taMin4(), midTemperatureDto.taMax4(), midTemperatureDto.taMin5(), midTemperatureDto.taMax5(), midTemperatureDto.taMin6(), midTemperatureDto.taMax6(), midTemperatureDto.taMin7(), midTemperatureDto.taMax7());
        }
    }
}
