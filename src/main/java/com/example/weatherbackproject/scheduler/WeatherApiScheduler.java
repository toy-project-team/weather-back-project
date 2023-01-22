package com.example.weatherbackproject.scheduler;

import com.example.weatherbackproject.service.MidWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class WeatherApiScheduler {

    private static final String dateNow = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

    private final MidWeatherService midWeatherService;

    public WeatherApiScheduler(MidWeatherService midWeatherService) {
        this.midWeatherService = midWeatherService;
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void createMidRainAndCloud() {
        midWeatherService.createMidLandFcst(dateNow);
        midWeatherService.createMidTa(dateNow);
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void updateMidRainAndCloud() {
        midWeatherService.updateMidLandFcst(dateNow);
        midWeatherService.updateMidTa(dateNow);
    }
}
