package com.example.weatherbackproject.scheduler;

import com.example.weatherbackproject.service.MidWeatherCommandService;
import com.example.weatherbackproject.service.ShortWeatherCommandService;
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
    private final ShortWeatherCommandService shortWeatherCommandService;

    public WeatherApiScheduler(MidWeatherCommandService midWeatherCommandService, ShortWeatherCommandService shortWeatherCommandService) {
        this.midWeatherCommandService = midWeatherCommandService;
        this.shortWeatherCommandService = shortWeatherCommandService;
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

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 2 * * *", zone="Asia/Seoul")
    public void createTwoMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "0200");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 5 * * *", zone="Asia/Seoul")
    public void createFiveMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "0500");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 8 * * *", zone="Asia/Seoul")
    public void createEighMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "0800");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 11 * * *", zone="Asia/Seoul")
    public void createElevenMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "1100");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 14 * * *", zone="Asia/Seoul")
    public void createFourteenMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "1400");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 17 * * *", zone="Asia/Seoul")
    public void createSevenTeenMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "1700");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 20 * * *", zone="Asia/Seoul")
    public void createTwentyMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "2000");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 23 * * *", zone="Asia/Seoul")
    public void createTwentyThirdMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(dateNow, "2300");
    }
}
