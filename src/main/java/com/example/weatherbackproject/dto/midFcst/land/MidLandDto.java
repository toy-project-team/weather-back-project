package com.example.weatherbackproject.dto.midFcst.land;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
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
        @JsonProperty("rnSt8") int rnSt8,
        @JsonProperty("wf3Am") String wf3Am,
        @JsonProperty("wf3Pm") String wf3Pm,
        @JsonProperty("wf4Am") String wf4Am,
        @JsonProperty("wf4Pm") String wf4Pm,
        @JsonProperty("wf5Am") String wf5Am,
        @JsonProperty("wf5Pm") String wf5Pm,
        @JsonProperty("wf6Am") String wf6Am,
        @JsonProperty("wf6Pm") String wf6Pm,
        @JsonProperty("wf7Am") String wf7Am,
        @JsonProperty("wf7Pm") String wf7Pm,
        @JsonProperty("wf8") String wf8
) {
}
