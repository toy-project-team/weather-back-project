package com.example.weatherbackproject.infra;

import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;

import java.net.URI;

public class FakeMidTaWeatherApiClient implements WeatherApiClient<MidTemperatureDto> {
    @Override
    public MidTemperatureDto requestWeather(URI uri) {
        return MidTemperatureDto.builder()
                .taMin3(5)
                .taMax3(5)
                .taMin4(5)
                .taMax4(6)
                .taMin5(6)
                .taMax5(6)
                .taMin6(7)
                .taMax6(7)
                .taMin7(7)
                .taMax7(8)
                .taMin8(8)
                .taMax8(8)
                .build();
    }
}
