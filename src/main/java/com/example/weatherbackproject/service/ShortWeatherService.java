package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.RegionCoordinate;
import com.example.weatherbackproject.domain.RegionCoordinateRepository;
import com.example.weatherbackproject.dto.shortFcst.vilage.ShortVilageDto;
import com.example.weatherbackproject.infra.ShortWeatherApiClient;
import com.example.weatherbackproject.infra.ShortWeatherUriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
public class ShortWeatherService {

    private final ShortWeatherApiClient shortWeatherApiClient;
    private final ShortWeatherUriBuilder shortWeatherUriBuilder;
    private final RegionCoordinateRepository regionCoordinateRepository;

    public ShortWeatherService(ShortWeatherApiClient shortWeatherApiClient, ShortWeatherUriBuilder shortWeatherUriBuilder, RegionCoordinateRepository regionCoordinateRepository) {
        this.shortWeatherApiClient = shortWeatherApiClient;
        this.shortWeatherUriBuilder = shortWeatherUriBuilder;
        this.regionCoordinateRepository = regionCoordinateRepository;
    }

    public void createShortVilageFcst(String date, String baseTime) {
        List<RegionCoordinate> regionCoordinates = regionCoordinateRepository.findAll();

        for (RegionCoordinate regionCoordinate : regionCoordinates) {
            URI uri = shortWeatherUriBuilder.buildUriByVilageFcst(date , baseTime, 60, 127);
            List<ShortVilageDto> shortVilageDtos = shortWeatherApiClient.requestShortVilageFcst(uri);

            // 강수확률 POP, 강수량 PCP, 적설량 SNO, 하늘상태 SKY, 1시간 기온 TMP, TMN 일최저기온, TMX 일 최고기온
            for (ShortVilageDto shortVilageDto : shortVilageDtos) {

            }
        }
    }
}
