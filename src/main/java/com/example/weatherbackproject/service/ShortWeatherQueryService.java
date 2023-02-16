package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.shortFcst.ShortWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ShortWeatherQueryService {

    private final ShortWeatherRepository shortWeatherRepository;
    private final RegionCoordinateRepository regionCoordinateRepository;

    public ShortWeatherQueryService(ShortWeatherRepository shortWeatherRepository, RegionCoordinateRepository regionCoordinateRepository) {
        this.shortWeatherRepository = shortWeatherRepository;
        this.regionCoordinateRepository = regionCoordinateRepository;
    }


    public List<ShortWeatherResponse> getShortWeatherList(String city, String state, LocalDateTime now) {
        RegionCoordinate regionCoordinate = regionCoordinateRepository.findByCityAndState(city, state).orElseThrow();

        List<ShortWeather> shortWeathers = shortWeatherRepository.findByRegionCodeIdAndInquiryDateGreaterThanEqualOrderByInquiryDateAsc(regionCoordinate.getId(), now);

        return shortWeathers.stream()
                .map(shortWeather -> ShortWeatherResponse.builder()
                        .date(shortWeather.getInquiryDate())
                        .rainProbability(shortWeather.getRainProbability())
                        .precipitationForm(shortWeather.getPrecipitationForm())
                        .rainPrecipitation(shortWeather.getRainPrecipitation())
                        .snowAmount(shortWeather.getSnowAmount())
                        .cloudState(shortWeather.getCloudState())
                        .temperature(shortWeather.getHourTemperature())
                        .humidity(shortWeather.getHumidity())
                        .build())
                .collect(toList());
    }
}
