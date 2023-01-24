package com.example.weatherbackproject.dto.shortFcst.vilage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ShortVilageItems {

    @JsonProperty("item")
    private List<ShortVilageDto> item;
}
