package com.example.weatherbackproject.scheduler;

import com.example.weatherbackproject.service.MidWeatherApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class WeatherApiScheduler {

    private final MidWeatherApiService midWeatherApiService;

    public WeatherApiScheduler(MidWeatherApiService midWeatherApiService) {
        this.midWeatherApiService = midWeatherApiService;
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void createMidRainAndCloud() {
        midWeatherApiService.requestMidLandFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "0600");
    }
}
