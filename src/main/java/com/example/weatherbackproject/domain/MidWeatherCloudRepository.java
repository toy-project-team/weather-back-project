package com.example.weatherbackproject.domain;

import java.util.List;

public interface MidWeatherCloudRepository {

    MidWeatherCloud save(MidWeatherCloud midWeatherCloud);

    List<MidWeatherCloud> findAllByInquiryDate(String date);
}
