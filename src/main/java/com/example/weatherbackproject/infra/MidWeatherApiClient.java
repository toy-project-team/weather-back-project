package com.example.weatherbackproject.infra;

import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.land.MidLandResultApiDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureResultApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
public class MidWeatherApiClient {

    private final RestTemplate restTemplate;

    public MidWeatherApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public MidLandDto requestMidLandFcst(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        MidLandResultApiDto midLandResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidLandResultApiDto.class).getBody();

        if (midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
            throw new RuntimeException("");
        }

        return midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
    }

    public MidTemperatureDto requestMidTa(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        MidTemperatureResultApiDto midTemperatureResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidTemperatureResultApiDto.class).getBody();

        if (midTemperatureResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
            throw new RuntimeException("");
        }

        return midTemperatureResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
    }
}
