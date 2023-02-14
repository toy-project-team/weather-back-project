package com.example.weatherbackproject.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ShortWeatherRepository {

    ShortWeather save(ShortWeather shortWeather);

    List<ShortWeather> findByRegionCodeIdAndInquiryDateGreaterThanEqualOrderByInquiryDateAsc(Long regionCodeId, LocalDateTime date);
}
