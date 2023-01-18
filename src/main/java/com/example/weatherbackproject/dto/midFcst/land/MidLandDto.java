package com.example.weatherbackproject.dto.midFcst.land;

import com.example.weatherbackproject.domain.MidWeatherCloud;
import com.example.weatherbackproject.domain.MidWeatherRain;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MidLandDto(
        @JsonProperty("rnSt3Am") int rnSt3Am,
        @JsonProperty("rnSt3Pm") int rnSt3Pm,
        @JsonProperty("rnSt4Am") int rnSt4Am,
        @JsonProperty("rnSt4Pm") int rnSt4Pm,
        @JsonProperty("rnSt5Am") int rnSt5Am,
        @JsonProperty("rnSt5Pm") int rnSt5Pm,
        @JsonProperty("rnSt6Am") int rnSt6Am,
        @JsonProperty("rnSt6Pm") int rnSt6Pm,
        @JsonProperty("rnSt7Am") int rnSt7Am,
        @JsonProperty("rnSt7Pm") int rnSt7Pm,
        @JsonProperty("wf3Am") String wf3Am,
        @JsonProperty("wf3Pm") String wf3Pm,
        @JsonProperty("wf4Am") String wf4Am,
        @JsonProperty("wf4Pm") String wf4Pm,
        @JsonProperty("wf5Am") String wf5Am,
        @JsonProperty("wf5Pm") String wf5Pm,
        @JsonProperty("wf6Am") String wf6Am,
        @JsonProperty("wf6Pm") String wf6Pm,
        @JsonProperty("wf7Am") String wf7Am,
        @JsonProperty("wf7Pm") String wf7Pm
) {

    public MidWeatherRain toMidWeatherRain() {
        return MidWeatherRain.builder()
                .rainFall3Am(rnSt3Am)
                .rainFall3Pm(rnSt3Pm)
                .rainFall4Am(rnSt4Am)
                .rainFall4Pm(rnSt4Pm)
                .rainFall5Am(rnSt5Am)
                .rainFall5Pm(rnSt5Pm)
                .rainFall6Am(rnSt6Am)
                .rainFall6Pm(rnSt6Pm)
                .rainFall7Am(rnSt7Am)
                .rainFall7Pm(rnSt7Pm)
                .build();
    }

    public MidWeatherCloud toMidWeatherCloud() {
        return MidWeatherCloud.builder()
                .cloud3Am(wf3Am)
                .cloud3Pm(wf3Pm)
                .cloud4Am(wf4Am)
                .cloud4Pm(wf4Pm)
                .cloud5Am(wf5Am)
                .cloud5Pm(wf5Pm)
                .cloud6Am(wf6Am)
                .cloud6Pm(wf6Pm)
                .cloud7Am(wf7Am)
                .cloud7Pm(wf7Pm)
                .build();
    }
}
