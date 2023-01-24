package com.example.weatherbackproject.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class MidWeatherUriBuilder {

    @Value("${weather.rest.api.key}")
    private String weatherRestApiKey;

    private static final String MID_WEATHER_LAND_FCST_URL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
    private static final String MID_WEATHER_TA_URL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa";

    public URI buildUriByLandFcst(String regionCode, String date) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(MID_WEATHER_LAND_FCST_URL);
        uriBuilder.queryParam("serviceKey", weatherRestApiKey);
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
        uriBuilder.queryParam("serviceKey", weatherRestApiKey);
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
