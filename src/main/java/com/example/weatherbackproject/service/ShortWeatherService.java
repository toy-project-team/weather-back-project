package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.RegionCoordinate;
import com.example.weatherbackproject.domain.RegionCoordinateRepository;
import com.example.weatherbackproject.domain.ShortWeather;
import com.example.weatherbackproject.domain.ShortWeatherRepository;
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
    private final ShortWeatherRepository shortWeatherRepository;

    private final ShortWeatherApiClient shortWeatherApiClient;
    private final ShortWeatherUriBuilder shortWeatherUriBuilder;
    private final RegionCoordinateRepository regionCoordinateRepository;

    public ShortWeatherService(ShortWeatherApiClient shortWeatherApiClient, ShortWeatherUriBuilder shortWeatherUriBuilder, RegionCoordinateRepository regionCoordinateRepository,
                               ShortWeatherRepository shortWeatherRepository) {
        this.shortWeatherApiClient = shortWeatherApiClient;
        this.shortWeatherUriBuilder = shortWeatherUriBuilder;
        this.regionCoordinateRepository = regionCoordinateRepository;
        this.shortWeatherRepository = shortWeatherRepository;
    }

    public void createShortVilageFcst(String date, String baseTime) {
        List<RegionCoordinate> regionCoordinates = regionCoordinateRepository.findAll();

        for (RegionCoordinate regionCoordinate : regionCoordinates) {
            URI uri = shortWeatherUriBuilder.buildUriByVilageFcst(date , baseTime, regionCoordinate.getNx(), regionCoordinate.getNy());
            List<ShortVilageDto> shortVilageDtos = shortWeatherApiClient.requestShortVilageFcst(uri);

            String standDate = shortVilageDtos.get(0).fcstDate();
            String standTime = shortVilageDtos.get(0).fcstTime();
            int rainProbability = 0;            // 강수 확률
            String precipitationForm = "";      // 강수 형태
            String rainPrecipitation = "";      // 강수량
            String snowAmount = "";             // 적설량
            String cloudState = "";             // 구름양
            int hourTemperature = 0;            // 기온
            int humidity = 0;                   // 습도

            // 강수확률 POP, 강수형태 PTY, 강수량 PCP, 적설량 SNO, 하늘상태 SKY, 1시간 기온 TMP, 습도 REH
            for (ShortVilageDto shortVilageDto : shortVilageDtos) {
                if (!standDate.equals(shortVilageDto.fcstDate()) || !standTime.equals(shortVilageDto.fcstTime())) {
                    shortWeatherRepository.save(
                            ShortWeather.builder()
                                    .regionCodeId(1L)
                                    .inquiryDate(standDate)
                                    .baseTime(standTime)
                                    .rainProbability(rainProbability)
                                    .precipitationForm(precipitationForm)
                                    .rainPrecipitation(rainPrecipitation)
                                    .snowAmount(snowAmount)
                                    .cloudState(cloudState)
                                    .hourTemperature(hourTemperature)
                                    .humidity(humidity)
                                    .build()
                    );

                    standDate = shortVilageDto.fcstDate();
                    standTime = shortVilageDto.fcstTime();
                }

                if (shortVilageDto.category().equals("POP")) {
                    rainProbability = Integer.parseInt(shortVilageDto.fcstValue());
                }

                if (shortVilageDto.category().equals("PTY")) {
                    precipitationForm = shortVilageDto.fcstValue();
                }

                if (shortVilageDto.category().equals("PCP")) {
                    rainPrecipitation = shortVilageDto.fcstValue();
                }

                if (shortVilageDto.category().equals("SNO")) {
                    snowAmount = shortVilageDto.fcstValue();
                }

                if (shortVilageDto.category().equals("SKY")) {
                    cloudState = shortVilageDto.fcstValue();
                }

                if (shortVilageDto.category().equals("TMP")) {
                    hourTemperature = Integer.parseInt(shortVilageDto.fcstValue());
                }

                if (shortVilageDto.category().equals("TMP")) {
                    hourTemperature = Integer.parseInt(shortVilageDto.fcstValue());
                }

                if (shortVilageDto.category().equals("REH")) {
                    humidity = Integer.parseInt(shortVilageDto.fcstValue());
                }
            }
        }
    }
}
