package com.example.weatherbackproject.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ShortWeatherRepositoryCustom {

    List<ShortWeather> findShortWeatherCurrentTime(LocalDateTime baseDate, Long regionCodeId);
}
