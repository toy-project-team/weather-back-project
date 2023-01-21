package com.example.weatherbackproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class MidWeatherUriBuilderService {

    @Value("${weather.rest.api.mid.key}")
    private String weatherRestApiMidKey;

    private static final String MID_WEATHER_LAND_FCST_URL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
    private static final String MID_WEATHER_TA_URL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa";

    public URI buildUriByLandFcst(String regionCode, String date) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(MID_WEATHER_LAND_FCST_URL);
        uriBuilder.queryParam("serviceKey", weatherRestApiMidKey);
        uriBuilder.queryParam("dataType", "JSON");
        uriBuilder.queryParam("numOfRows", 10);
        uriBuilder.queryParam("pageNo", 1);
        uriBuilder.queryParam("regId", regionCode);
        uriBuilder.queryParam("tmFc", date);

        String uri = uriBuilder.build().toUriString();

        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public URI buildUriByTa(String regionCode, String date) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(MID_WEATHER_TA_URL);
        uriBuilder.queryParam("serviceKey", weatherRestApiMidKey);
        uriBuilder.queryParam("dataType", "JSON");
        uriBuilder.queryParam("numOfRows", 10);
        uriBuilder.queryParam("pageNo", 1);
        uriBuilder.queryParam("regId", regionCode);
        uriBuilder.queryParam("tmFc", date);

        String uri = uriBuilder.build().toUriString();

        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
