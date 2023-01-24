package com.example.weatherbackproject.infra;

import com.example.weatherbackproject.dto.shortFcst.vilage.ShortVilageDto;
import com.example.weatherbackproject.dto.shortFcst.vilage.ShortVilageResultApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class ShortWeatherApiClient {

    private final RestTemplate restTemplate;

    public ShortWeatherApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<ShortVilageDto> requestShortVilageFcst(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        ShortVilageResultApiDto shortVilageResultApiDto = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ShortVilageResultApiDto.class).getBody();

        if (shortVilageResultApiDto.getCommonResponse().getBody().getItems().getItem().isEmpty()) {
            throw new RuntimeException("");
        }

        return shortVilageResultApiDto.getCommonResponse().getBody().getItems().getItem();
    }
}
