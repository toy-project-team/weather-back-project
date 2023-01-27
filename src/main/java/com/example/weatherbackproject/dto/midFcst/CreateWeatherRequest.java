package com.example.weatherbackproject.dto.midFcst;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateWeatherRequest {

    private String date;
    private String time;
}
