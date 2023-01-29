package com.example.weatherbackproject.scheduler;

import com.example.weatherbackproject.service.MidWeatherCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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

    @Async("schedulerExecutor")
    @Scheduled(cron = "1 0 0 * * *", zone="Asia/Seoul")
    public void createMidRainAndCloud() {
        midWeatherCommandService.createMidLandFcst();
        midWeatherCommandService.createMidTa();
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 10 6 * * *", zone="Asia/Seoul")
    public void updateSixMidRainAndCloud() {
        midWeatherCommandService.updateMidLandFcst(dateNow, "0600");
        midWeatherCommandService.updateMidTa(dateNow, "0600");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 10 18 * * *", zone="Asia/Seoul")
    public void updateEighteenMidRainAndCloud() {
        midWeatherCommandService.updateMidLandFcst(dateNow, "1800");
        midWeatherCommandService.updateMidTa(dateNow, "1800");
    }
}
