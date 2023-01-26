package com.example.weatherbackproject.scheduler;

import com.example.weatherbackproject.service.MidWeatherCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class WeatherApiScheduler {

    private static final String dateNow = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

    private final MidWeatherCommandService midWeatherCommandService;

    public WeatherApiScheduler(MidWeatherCommandService midWeatherCommandService) {
        this.midWeatherCommandService = midWeatherCommandService;
    }

    @Scheduled(cron = "0 10 6 * * *")
    public void createMidRainAndCloud() {
        midWeatherCommandService.createMidLandFcst(dateNow);
        midWeatherCommandService.createMidTa(dateNow);
    }

    @Scheduled(cron = "0 10 18 * * *")
    public void updateMidRainAndCloud() {
        midWeatherCommandService.updateMidLandFcst(dateNow);
        midWeatherCommandService.updateMidTa(dateNow);
    }
}
