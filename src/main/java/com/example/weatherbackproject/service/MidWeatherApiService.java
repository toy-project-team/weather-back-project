package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.land.MidLandResultApiDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureResultApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
public class MidWeatherApiService {

    private final RestTemplate restTemplate;
    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;
    private final MidWeatherUriBuilderService midWeatherUriBuilderService;
    private final MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherApiService(RestTemplateBuilder restTemplateBuilder, MidWeatherUriBuilderService midWeatherUriBuilderService, RegionCodeRepository regionCodeRepository,
                                MidWeatherRainRepository midWeatherRainRepository, MidWeatherCloudRepository midWeatherCloudRepository, MidWeatherTemperatureRepository midWeatherTemperatureRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.midWeatherUriBuilderService = midWeatherUriBuilderService;
        this.regionCodeRepository = regionCodeRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherCloudRepository = midWeatherCloudRepository;
        this.midWeatherTemperatureRepository = midWeatherTemperatureRepository;
    }

    public void createMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + "0600");

            MidLandDto midLandDto = requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midLandDto.toMidWeatherRain(code.getId(), date);
            MidWeatherCloud midWeatherCloud = midLandDto.toMidWeatherCloud(code.getId(), date);

            midWeatherRainRepository.save(midWeatherRain);
            midWeatherCloudRepository.save(midWeatherCloud);
        }
    }

    public void updateMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();
        List<MidWeatherRain> midWeatherRains = midWeatherRainRepository.findAllByInquiryDate(date);
        List<MidWeatherCloud> midWeatherClouds = midWeatherCloudRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + "1800");

            MidLandDto midLandDto = requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midWeatherRains.stream()
                    .filter(rain -> rain.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherRain.updateRain(midLandDto.rnSt3Am(), midLandDto.rnSt3Pm(), midLandDto.rnSt4Am(), midLandDto.rnSt4Pm(), midLandDto.rnSt5Am(), midLandDto.rnSt5Pm(), midLandDto.rnSt6Am(), midLandDto.rnSt6Pm(), midLandDto.rnSt7Am(), midLandDto.rnSt7Pm());

            MidWeatherCloud midWeatherCloud = midWeatherClouds.stream()
                    .filter(cloud -> cloud.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherCloud.updateCloud(midLandDto.wf3Am(), midLandDto.wf3Pm(), midLandDto.wf4Am(), midLandDto.wf4Pm(), midLandDto.wf5Am(), midLandDto.wf5Pm(), midLandDto.wf6Am(), midLandDto.wf6Pm(), midLandDto.wf7Am(), midLandDto.wf7Pm());
        }
    }

    private MidLandDto requestMidLandFcst(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        MidLandResultApiDto midLandResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidLandResultApiDto.class).getBody();

        if (midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
            throw new RuntimeException("");
        }

        return midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
    }

    public void createMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date);

            MidWeatherTemperature midWeatherTemperature = requestMidTa(uri).toMidWeatherTemperature();

            midWeatherTemperatureRepository.save(midWeatherTemperature);
        }
    }

    public void updateMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();
        List<MidWeatherTemperature> midWeatherTemperatures = midWeatherTemperatureRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date);

            MidTemperatureDto midTemperatureDto = requestMidTa(uri);

            MidWeatherTemperature midWeatherTemperature = midWeatherTemperatures.stream()
                    .filter(tem -> tem.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherTemperature.updateTemperature(midTemperatureDto.taMin3(), midTemperatureDto.taMax3(), midTemperatureDto.taMin4(), midTemperatureDto.taMax4(), midTemperatureDto.taMin5(), midTemperatureDto.taMax5(), midTemperatureDto.taMin6(), midTemperatureDto.taMax6(), midTemperatureDto.taMin7(), midTemperatureDto.taMax7());
        }
    }

    private MidTemperatureDto requestMidTa(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        MidTemperatureResultApiDto midTemperatureResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidTemperatureResultApiDto.class).getBody();

        if (midTemperatureResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
            throw new RuntimeException("");
        }

        return midTemperatureResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
    }
}
