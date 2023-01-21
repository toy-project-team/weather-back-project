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

    public void requestMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);

            MidLandResultApiDto midLandResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidLandResultApiDto.class).getBody();

            if (midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
                throw new RuntimeException("");
            }

            MidLandDto midLandDto = midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
            MidWeatherRain midWeatherRain = midLandDto.toMidWeatherRain();
            MidWeatherCloud midWeatherCloud = midLandDto.toMidWeatherCloud();

            midWeatherRainRepository.save(midWeatherRain);
            midWeatherCloudRepository.save(midWeatherCloud);
        }
    }

    public void requestMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);

            MidTemperatureResultApiDto midTemperatureResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidTemperatureResultApiDto.class).getBody();

            if (midTemperatureResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
                throw new RuntimeException("");
            }

            MidTemperatureDto midTemperatureDto = midTemperatureResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
            MidWeatherTemperature midWeatherTemperature = midTemperatureDto.toMidWeatherTemperature();

            midWeatherTemperatureRepository.save(midWeatherTemperature);
        }
    }
}
