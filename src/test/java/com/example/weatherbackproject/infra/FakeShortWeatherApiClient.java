package com.example.weatherbackproject.infra;

import com.example.weatherbackproject.dto.shortFcst.vilage.ShortVilageDto;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class FakeShortWeatherApiClient implements WeatherApiClient<List<ShortVilageDto>> {

    @Override
    public List<ShortVilageDto> requestWeather(URI uri) {
        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        return Arrays.asList(
                new ShortVilageDto(now, "0200", "POP", now, "0200", "10", 37.56100277777777, 126.99964166666666),
                new ShortVilageDto(now, "0200", "PTY", now, "0200", "비", 37.56100277777777, 126.99964166666666),
                new ShortVilageDto(now, "0200", "PCP", now, "0200", "5", 37.56100277777777, 126.99964166666666),
                new ShortVilageDto(now, "0200", "SNO", now, "0200", "0", 37.56100277777777, 126.99964166666666),
                new ShortVilageDto(now, "0200", "SKY", now, "0200", "구름많음", 37.56100277777777, 126.99964166666666),
                new ShortVilageDto(now, "0200", "TMP", now, "0200", "15", 37.56100277777777, 126.99964166666666),
                new ShortVilageDto(now, "0200", "REH", now, "0200", "70", 37.56100277777777, 126.99964166666666)
        );
    }
}
