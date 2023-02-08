package com.example.weatherbackproject.infra;

import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.land.MidLandResultApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
@Qualifier("midLandFcstWeatherApiClient")
public class MidLandFcstWeatherApiClientImpl implements WeatherApiClient<MidLandDto> {

    private final RestTemplate restTemplate;

    public MidLandFcstWeatherApiClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public MidLandDto requestWeather(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        MidLandResultApiDto midLandResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, MidLandResultApiDto.class).getBody();

        if (midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().size() == 0) {
            throw new RuntimeException("");
        }

        return midLandResultApiDto.getCommonResponse().getBody().getItems().getItem().get(0);
    }
}
