package com.example.weatherbackproject.documentation;

import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import com.example.weatherbackproject.service.MidWeatherQueryService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class MidWeatherDocumentation extends BaseDocumentation {

    @MockBean
    private MidWeatherQueryService midWeatherQueryService;

    @DisplayName("요일 날씨 리스트 조회")
    @Test
    void getMidWeatherList() {
        List<MidWeatherResponse> midWeatherResponses = List.of(
                MidWeatherResponse.builder()
                        .orders(0)
                        .date("20230128")
                        .day("오늘")
                        .rainAm(20)
                        .rainPm(20)
                        .tempMin(5)
                        .tempMax(5)
                        .cloudAm("맑음")
                        .cloudPm("맑음")
                        .build()
        );

        when(midWeatherQueryService.midWeatherList(anyString(), anyDouble(), anyDouble())).thenReturn(midWeatherResponses);

        RestAssured
                .given(spec).log().all()
                .filter(document("/mid/list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("state").description("시도(서울, 인천, 경기도, 강원도영서, 강원도영동, 대전, 세종, 충청남도, 충청북도, 광주, 전라남도, 전라북도, 대구, 경상북도, 부산, 울산, 경상남도, 제주도)"),
                                parameterWithName("latitude").description("위도"),
                                parameterWithName("longitude").description("경도")
                        ),
                        responseFields(
                                fieldWithPath("[].orders").type(JsonFieldType.NUMBER).description("순서"),
                                fieldWithPath("[].date").type(JsonFieldType.STRING).description("일자"),
                                fieldWithPath("[].day").type(JsonFieldType.STRING).description("요일"),
                                fieldWithPath("[].rainAm").type(JsonFieldType.NUMBER).description("오전 강수량"),
                                fieldWithPath("[].rainPm").type(JsonFieldType.NUMBER).description("오후 강수량"),
                                fieldWithPath("[].tempMin").type(JsonFieldType.NUMBER).description("최저 기온"),
                                fieldWithPath("[].tempMax").type(JsonFieldType.NUMBER).description("최고 기온"),
                                fieldWithPath("[].cloudAm").type(JsonFieldType.STRING).description("오전 구름양"),
                                fieldWithPath("[].cloudPm").type(JsonFieldType.STRING).description("오후 구름양")
                        )))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("state", "경기도")
                .queryParam("latitude", 37.63317777777778)
                .queryParam("longitude", 127.21863333333333)
                .when().get("/api/mid")
                .then().log().all().extract();
    }
}
