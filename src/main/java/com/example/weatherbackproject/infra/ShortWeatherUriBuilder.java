package com.example.weatherbackproject.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class ShortWeatherUriBuilder {

    @Value("${weather.rest.api.key}")
    private String weatherRestApiKey;

    private static final String SHORT_WEATHER_VILAGE_FCST_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";

    public URI buildUriByVilageFcst(String date, String baseTime, int nx, int ny) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(SHORT_WEATHER_VILAGE_FCST_URL);
        uriBuilder.queryParam("serviceKey", weatherRestApiKey);
        uriBuilder.queryParam("dataType", "JSON");
        uriBuilder.queryParam("numOfRows", 1000);
        uriBuilder.queryParam("pageNo", 1);
        uriBuilder.queryParam("base_date", date);
        uriBuilder.queryParam("base_time", baseTime);
        uriBuilder.queryParam("nx", nx);
        uriBuilder.queryParam("ny", ny);

        String uri = uriBuilder.build().toUriString();

        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
