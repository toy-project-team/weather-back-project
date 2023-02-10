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
        midWeatherCommandService.updateMidLandFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "0600");
        midWeatherCommandService.updateMidTa(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "0600");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 10 18 * * *", zone="Asia/Seoul")
    public void updateEighteenMidRainAndCloud() {
        midWeatherCommandService.updateMidLandFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "1800");
        midWeatherCommandService.updateMidTa(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "1800");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 2 * * *", zone="Asia/Seoul")
    public void createTwoMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "0200");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 5 * * *", zone="Asia/Seoul")
    public void createFiveMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "0500");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 8 * * *", zone="Asia/Seoul")
    public void createEighMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "0800");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 11 * * *", zone="Asia/Seoul")
    public void createElevenMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "1100");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 14 * * *", zone="Asia/Seoul")
    public void createFourteenMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "1400");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 17 * * *", zone="Asia/Seoul")
    public void createSevenTeenMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "1700");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 20 * * *", zone="Asia/Seoul")
    public void createTwentyMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "2000");
    }

    @Async("schedulerExecutor")
    @Scheduled(cron = "0 11 23 * * *", zone="Asia/Seoul")
    public void createTwentyThirdMidWeather() {
        shortWeatherCommandService.createShortVilageFcst(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), "2300");
    }
}
