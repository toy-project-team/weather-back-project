package com.example.weatherbackproject.infra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class ShortWeatherUriBuilderTest {

    private ShortWeatherUriBuilder shortWeatherUriBuilder;

    @BeforeEach
    void setUp() {
        shortWeatherUriBuilder = new ShortWeatherUriBuilder();
    }

    @Test
    void 중기_날씨_LandFcst_URI_Address_생성() {
        URI uri = shortWeatherUriBuilder.buildUriByVilageFcst("20230207", "0200", 60, 127);
        String decodeUri = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);

        assertThat(decodeUri).isEqualTo("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey&dataType=JSON&numOfRows=1000&pageNo=1&base_date=20230207&base_time=0200&nx=60&ny=127");
    }
}
