package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.MidWeatherResponse;
import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;
import com.example.weatherbackproject.infra.MidWeatherApiClient;
import com.example.weatherbackproject.infra.MidWeatherUriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class MidWeatherService {

    private final MidWeatherApiClient midWeatherApiService;
    private final MidWeatherUriBuilder midWeatherUriBuilderService;
    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;
    private final MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherService(MidWeatherApiClient midWeatherApiService, MidWeatherUriBuilder midWeatherUriBuilderService, MidWeatherCloudRepository midWeatherCloudRepository, MidWeatherRainRepository midWeatherRainRepository, MidWeatherTemperatureRepository midWeatherTemperatureRepository, RegionCodeRepository regionCodeRepository) {
        this.midWeatherApiService = midWeatherApiService;
        this.midWeatherCloudRepository = midWeatherCloudRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherUriBuilderService = midWeatherUriBuilderService;
        this.midWeatherTemperatureRepository = midWeatherTemperatureRepository;
        this.regionCodeRepository = regionCodeRepository;
    }

    public List<MidWeatherResponse> midWeatherList() {
        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        Long codeId = 1L;

        MidWeatherRain midWeatherRain = midWeatherRainRepository.findByRegionCodeIdAndInquiryDate(codeId, now).orElseThrow();
        MidWeatherCloud midWeatherCloud = midWeatherCloudRepository.findByRegionCodeIdAndInquiryDate(codeId, now).orElseThrow();
        MidWeatherTemperature midWeatherTemperature = midWeatherTemperatureRepository.findByRegionCodeIdAndInquiryDate(codeId, now).orElseThrow();

        return Arrays.asList(
                toMidWeatherResponse(1, midWeatherRain.getRainFall3Am(), midWeatherRain.getRainFall3Pm(), midWeatherTemperature.getTemperature3Min(), midWeatherTemperature.getTemperature3Max(), midWeatherCloud.getCloud3Am(), midWeatherCloud.getCloud3Pm()),
                toMidWeatherResponse(2, midWeatherRain.getRainFall4Am(), midWeatherRain.getRainFall4Pm(), midWeatherTemperature.getTemperature4Min(), midWeatherTemperature.getTemperature4Max(), midWeatherCloud.getCloud4Am(), midWeatherCloud.getCloud4Pm()),
                toMidWeatherResponse(3, midWeatherRain.getRainFall5Am(), midWeatherRain.getRainFall5Pm(), midWeatherTemperature.getTemperature5Min(), midWeatherTemperature.getTemperature5Max(), midWeatherCloud.getCloud5Am(), midWeatherCloud.getCloud5Pm()),
                toMidWeatherResponse(4, midWeatherRain.getRainFall6Am(), midWeatherRain.getRainFall6Pm(), midWeatherTemperature.getTemperature6Min(), midWeatherTemperature.getTemperature6Max(), midWeatherCloud.getCloud6Am(), midWeatherCloud.getCloud6Pm()),
                toMidWeatherResponse(5, midWeatherRain.getRainFall7Am(), midWeatherRain.getRainFall7Pm(), midWeatherTemperature.getTemperature7Min(), midWeatherTemperature.getTemperature7Max(), midWeatherCloud.getCloud7Am(), midWeatherCloud.getCloud7Pm())
        );
    }

    private MidWeatherResponse toMidWeatherResponse(int orders, int rainAm, int rainPm, int tempMin, int tempMax, String cloudAm, String cloudPm) {
        return MidWeatherResponse.builder()
                .orders(orders)
                .rainAm(rainAm)
                .rainPm(rainPm)
                .cloudAm(cloudAm)
                .cloudPm(cloudPm)
                .tempMin(tempMin)
                .tempMax(tempMax)
                .build();
    }

    public void createMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();
        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + "0600");

            MidLandDto midLandDto = midWeatherApiService.requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midLandDto.toMidWeatherRain(code.getId(), date);
            MidWeatherCloud midWeatherCloud = midLandDto.toMidWeatherCloud(code.getId(), date);

            midWeatherRainRepository.save(midWeatherRain);
            midWeatherCloudRepository.save(midWeatherCloud);
        }
    }

    public void updateMidLandFcst(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();
        List<MidWeatherRain> midWeatherRains = midWeatherRainRepository.findAllByInquiryDate(date);
        List<MidWeatherCloud> midWeatherClouds = midWeatherCloudRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + "1800");

            MidLandDto midLandDto = midWeatherApiService.requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midWeatherRains.stream()
                    .filter(rain -> rain.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherRain.updateRain(midLandDto.rnSt4Am(), midLandDto.rnSt4Pm(), midLandDto.rnSt5Am(), midLandDto.rnSt5Pm(), midLandDto.rnSt6Am(), midLandDto.rnSt6Pm(), midLandDto.rnSt7Am(), midLandDto.rnSt7Pm());

            MidWeatherCloud midWeatherCloud = midWeatherClouds.stream()
                    .filter(cloud -> cloud.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherCloud.updateCloud(midLandDto.wf3Am(), midLandDto.wf3Pm(), midLandDto.wf4Am(), midLandDto.wf4Pm(), midLandDto.wf5Am(), midLandDto.wf5Pm(), midLandDto.wf6Am(), midLandDto.wf6Pm(), midLandDto.wf7Am(), midLandDto.wf7Pm());
        }
    }

    public void createMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();
        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date + "0600");

            MidWeatherTemperature midWeatherTemperature = midWeatherApiService.requestMidTa(uri).toMidWeatherTemperature(code.getId(), date);

            midWeatherTemperatureRepository.save(midWeatherTemperature);
        }
    }

    public void updateMidTa(String date) {
        List<RegionCode> regionCodes = regionCodeRepository.findAll();
        List<MidWeatherTemperature> midWeatherTemperatures = midWeatherTemperatureRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date + "1800");

            MidTemperatureDto midTemperatureDto = midWeatherApiService.requestMidTa(uri);

            MidWeatherTemperature midWeatherTemperature = midWeatherTemperatures.stream()
                    .filter(tem -> tem.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherTemperature.updateTemperature(midTemperatureDto.taMin4(), midTemperatureDto.taMax4(), midTemperatureDto.taMin5(), midTemperatureDto.taMax5(), midTemperatureDto.taMin6(), midTemperatureDto.taMax6(), midTemperatureDto.taMin7(), midTemperatureDto.taMax7());
        }
    }
}
