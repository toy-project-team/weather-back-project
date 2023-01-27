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

    @Scheduled(cron = "1 0 0 * * *")
    public void createMidRainAndCloud() {
        midWeatherCommandService.createMidLandFcst();
        midWeatherCommandService.createMidTa();
    }

    @Scheduled(cron = "0 10 6 * * *")
    public void updateSixMidRainAndCloud() {
        midWeatherCommandService.updateSixMidLandFcst(dateNow);
        midWeatherCommandService.updateSixMidTa(dateNow);
    }

    @Scheduled(cron = "0 10 18 * * *")
    public void updateEighteenMidRainAndCloud() {
        midWeatherCommandService.updateEighteenMidLandFcst(dateNow);
        midWeatherCommandService.updateEighteenMidTa(dateNow);
    }
}
