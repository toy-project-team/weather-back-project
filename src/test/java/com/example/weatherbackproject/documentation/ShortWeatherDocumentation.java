package com.example.weatherbackproject.documentation;

import com.example.weatherbackproject.dto.shortFcst.ShortWeatherResponse;
import com.example.weatherbackproject.service.ShortWeatherQueryService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class ShortWeatherDocumentation extends BaseDocumentation {

    @MockBean
    private ShortWeatherQueryService shortWeatherQueryService;

    @DisplayName("시간별 날씨 예보 리스트 조회")
    @Test
    void getShortWeatherList() {
        List<ShortWeatherResponse> shortWeatherResponses = List.of(
                ShortWeatherResponse.builder()
                        .date(LocalDateTime.now())
                        .rainProbability(10)
                        .precipitationForm("없음")
                        .rainPrecipitation("강수없음")
                        .snowAmount("적설없음")
                        .cloudState("맑음")
                        .temperature(0)
                        .humidity(0)
                        .build()
        );

        when(shortWeatherQueryService.getShortWeatherList(anyString(), anyString())).thenReturn(shortWeatherResponses);

        RestAssured
                .given(spec).log().all()
                .filter(document("/short/list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("city").description("시도(서울특별시, 인천광역시, 경기도, 강원도, 대전광역시, 세종특별자치시, 충청남도, 충청북도, 광주광역시, 전라남도, 전라북도, 대구광역시, 경상북도, 부산광역시, 울산광역시, 경상남도, 제주특별자치도)"),
                                parameterWithName("state").description("시군구")
                        ),
                        responseFields(
                                fieldWithPath("[].day").type(JsonFieldType.STRING).description("날짜"),
                                fieldWithPath("[].time").type(JsonFieldType.STRING).description("시간"),
                                fieldWithPath("[].rainProbability").type(JsonFieldType.NUMBER).description("강수 확률"),
                                fieldWithPath("[].precipitationForm").type(JsonFieldType.STRING).description("강수 형태"),
                                fieldWithPath("[].rainPrecipitation").type(JsonFieldType.STRING).description("강수량"),
                                fieldWithPath("[].snowAmount").type(JsonFieldType.STRING).description("적설량"),
                                fieldWithPath("[].cloudState").type(JsonFieldType.STRING).description("구름양"),
                                fieldWithPath("[].temperature").type(JsonFieldType.NUMBER).description("기온"),
                                fieldWithPath("[].humidity").type(JsonFieldType.NUMBER).description("습도")
                        )))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("city", "서울특별시")
                .queryParam("state", "송파구")
                .when().get("/api/short")
                .then().log().all().extract();
    }
}
