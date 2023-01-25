package com.example.weatherbackproject.api;

import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import com.example.weatherbackproject.service.MidWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MidWeatherController {

    private final MidWeatherService midWeatherService;

    public MidWeatherController(MidWeatherService midWeatherService) {
        this.midWeatherService = midWeatherService;
    }

    @GetMapping("/api/mid")
    public List<MidWeatherResponse> midWeatherList() {
        return midWeatherService.midWeatherList();
    }
}
