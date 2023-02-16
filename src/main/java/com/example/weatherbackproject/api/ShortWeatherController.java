package com.example.weatherbackproject.api;

import com.example.weatherbackproject.dto.shortFcst.ShortWeatherResponse;
import com.example.weatherbackproject.service.ShortWeatherCommandService;
import com.example.weatherbackproject.service.ShortWeatherQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ShortWeatherController {

    private final ShortWeatherCommandService shortWeatherCommandService;
    private final ShortWeatherQueryService shortWeatherQueryService;

    public ShortWeatherController(ShortWeatherCommandService shortWeatherCommandService, ShortWeatherQueryService shortWeatherQueryService) {
        this.shortWeatherCommandService = shortWeatherCommandService;
        this.shortWeatherQueryService = shortWeatherQueryService;
    }

    @GetMapping("/api/short")
    private List<ShortWeatherResponse> shortWeatherList(String city, String state) {
        LocalDateTime now = LocalDateTime.now();
        return shortWeatherQueryService.getShortWeatherList(city, state, now);
    }

    @GetMapping("/api/short/save")
    public void createShortWeather(String date, String time) {
        shortWeatherCommandService.createShortVilageFcst(date, time);
    }
}
