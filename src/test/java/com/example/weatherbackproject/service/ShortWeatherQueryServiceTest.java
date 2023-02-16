package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.shortFcst.ShortWeatherResponse;
import com.example.weatherbackproject.dto.shortFcst.vilage.ShortVilageDto;
import com.example.weatherbackproject.infra.FakeShortWeatherApiClient;
import com.example.weatherbackproject.infra.ShortWeatherUriBuilder;
import com.example.weatherbackproject.infra.WeatherApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ShortWeatherQueryServiceTest {

    private WeatherApiClient<List<ShortVilageDto>> weatherApiClient;
    private ShortWeatherUriBuilder shortWeatherUriBuilder;
    private RegionCoordinateRepository regionCoordinateRepository;
    private ShortWeatherRepository shortWeatherRepository;

    private ShortWeatherCommandService shortWeatherCommandService;
    private ShortWeatherQueryService shortWeatherQueryService;

    @BeforeEach
    void setUp() {
        weatherApiClient = new FakeShortWeatherApiClient();
        shortWeatherUriBuilder = new ShortWeatherUriBuilder();
        regionCoordinateRepository = new InMemoryRegionCoordinateRepository();
        shortWeatherRepository = new InMemoryShortWeatherRepository();

        shortWeatherCommandService = new ShortWeatherCommandService(weatherApiClient, shortWeatherUriBuilder, regionCoordinateRepository, shortWeatherRepository);
        shortWeatherQueryService = new ShortWeatherQueryService(shortWeatherRepository, regionCoordinateRepository);

        regionCoordinateRepository.save(RegionCoordinate.builder()
                .id(1L)
                .city("서울특별시")
                .state("중구")
                .latitude(37.56100277777777)
                .longitude(126.99964166666666)
                .nx(60)
                .ny(127)
                .build());
    }

    @Test
    void 단기_날씨_조회() {
        LocalDateTime now = LocalDateTime.now();
        String nowStr = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);

        shortWeatherCommandService.createShortVilageFcst(nowStr, "0200");

        LocalDateTime date = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 2, 0);
        List<ShortWeatherResponse> shortWeathers = shortWeatherQueryService.getShortWeatherList("서울특별시", "중구", date);

        assertAll(
                () -> assertThat(shortWeathers.size()).isEqualTo(2),
                () -> assertThat(shortWeathers.get(0).getRainProbability()).isEqualTo(10),
                () -> assertThat(shortWeathers.get(0).getPrecipitationForm()).isEqualTo("없음"),
                () -> assertThat(shortWeathers.get(0).getRainPrecipitation()).isEqualTo("5"),
                () -> assertThat(shortWeathers.get(0).getSnowAmount()).isEqualTo("0"),
                () -> assertThat(shortWeathers.get(0).getCloudState()).isEqualTo("맑음"),
                () -> assertThat(shortWeathers.get(0).getTemperature()).isEqualTo(15),
                () -> assertThat(shortWeathers.get(0).getHumidity()).isEqualTo(70)
        );
    }
}
