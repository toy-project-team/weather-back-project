package com.example.weatherbackproject.infra;

import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;

import java.net.URI;

public class FakeMidLandFcstWeatherApiClient implements WeatherApiClient<MidLandDto> {

    @Override
    public MidLandDto requestWeather(URI uri) {
        return MidLandDto.builder()
                .rnSt3Am(11)
                .rnSt3Pm(12)
                .rnSt4Am(13)
                .rnSt4Pm(14)
                .rnSt5Am(15)
                .rnSt5Pm(16)
                .rnSt6Am(17)
                .rnSt6Pm(18)
                .rnSt7Am(19)
                .rnSt7Pm(20)
                .rnSt8(21)
                .wf3Am("맑음")
                .wf3Pm("맑음")
                .wf4Am("맑음")
                .wf4Pm("맑음")
                .wf5Am("구름많음")
                .wf5Pm("구름많음")
                .wf6Am("구름많음")
                .wf6Pm("구름많음")
                .wf7Am("흐림")
                .wf7Pm("흐림")
                .wf8("흐림")
                .build();
    }
}
