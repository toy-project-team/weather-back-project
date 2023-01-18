package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.land.MidLandResultApiDto;
import lombok.extern.slf4j.Slf4j;
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

    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;

    private final RestTemplate restTemplate;
    private final MidWeatherUriBuilderService midWeatherUriBuilderService;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherApiService(RestTemplate restTemplate, MidWeatherUriBuilderService midWeatherUriBuilderService, RegionCodeRepository regionCodeRepository,
                                MidWeatherRainRepository midWeatherRainRepository,
                                MidWeatherCloudRepository midWeatherCloudRepository) {
        this.restTemplate = restTemplate;
        this.midWeatherUriBuilderService = midWeatherUriBuilderService;
        this.regionCodeRepository = regionCodeRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherCloudRepository = midWeatherCloudRepository;
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
}
