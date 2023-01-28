package com.example.weatherbackproject.api;

import com.example.weatherbackproject.dto.midFcst.CreateWeatherRequest;
import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import com.example.weatherbackproject.service.MidWeatherCommandService;
import com.example.weatherbackproject.service.MidWeatherQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MidWeatherController {

    private final MidWeatherQueryService midWeatherService;
    private final MidWeatherCommandService midWeatherCommandService;

    public MidWeatherController(MidWeatherQueryService midWeatherService, MidWeatherCommandService midWeatherCommandService) {
        this.midWeatherService = midWeatherService;
        this.midWeatherCommandService = midWeatherCommandService;
    }

    @GetMapping("/api/mid")
    public List<MidWeatherResponse> midWeatherList(@RequestParam String state, @RequestParam double latitude, @RequestParam double longitude) {
        return midWeatherService.midWeatherList(state, latitude, longitude);
    }

    @PostMapping("/api/mid")
    public void createMidWeather() {
        midWeatherCommandService.createMidLandFcst();
        midWeatherCommandService.createMidTa();
    }

    @PatchMapping("/api/mid")
    public void updateMidWeather(@RequestBody CreateWeatherRequest createWeatherRequest) {
        midWeatherCommandService.updateMidLandFcst(createWeatherRequest.getDate(), createWeatherRequest.getTime());
        midWeatherCommandService.updateMidTa(createWeatherRequest.getDate(), createWeatherRequest.getTime());
    }
}
