package com.example.weatherbackproject.infra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;


class MidWeatherUriBuilderTest {

    private MidWeatherUriBuilder midWeatherUriBuilder;

    @BeforeEach
    void setUp() {
        midWeatherUriBuilder = new MidWeatherUriBuilder();
    }

    @Test
    void 중기_날씨_LandFcst_URI_Address_생성() {
        URI uri = midWeatherUriBuilder.buildUriByLandFcst("11B00000", "20230207");
        String decodeUri = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);

        assertThat(decodeUri).isEqualTo("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey&dataType=JSON&numOfRows=10&pageNo=1&regId=11B00000&tmFc=20230207");
    }

    @Test
    void 중기_날씨_Ta_URI_Address_생성() {
        URI uri = midWeatherUriBuilder.buildUriByTa("11B00000", "20230207");
        String decodeUri = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);

        assertThat(decodeUri).isEqualTo("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey&dataType=JSON&numOfRows=10&pageNo=1&regId=11B00000&tmFc=20230207");
    }
}
