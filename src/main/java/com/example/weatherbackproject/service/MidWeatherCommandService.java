package com.example.weatherbackproject.service;

import com.example.weatherbackproject.domain.*;
import com.example.weatherbackproject.dto.midFcst.land.MidLandDto;
import com.example.weatherbackproject.dto.midFcst.temperature.MidTemperatureDto;
import com.example.weatherbackproject.infra.MidWeatherApiClient;
import com.example.weatherbackproject.infra.MidWeatherUriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Transactional
@Service
public class MidWeatherCommandService {

    private final MidWeatherApiClient midWeatherApiService;
    private final MidWeatherUriBuilder midWeatherUriBuilderService;
    private final MidWeatherCloudRepository midWeatherCloudRepository;
    private final MidWeatherRainRepository midWeatherRainRepository;
    private final MidWeatherTemperatureRepository midWeatherTemperatureRepository;
    private final RegionCodeRepository regionCodeRepository;

    public MidWeatherCommandService(MidWeatherApiClient midWeatherApiService, MidWeatherUriBuilder midWeatherUriBuilderService, MidWeatherCloudRepository midWeatherCloudRepository, MidWeatherRainRepository midWeatherRainRepository, MidWeatherTemperatureRepository midWeatherTemperatureRepository, RegionCodeRepository regionCodeRepository) {
        this.midWeatherApiService = midWeatherApiService;
        this.midWeatherUriBuilderService = midWeatherUriBuilderService;
        this.midWeatherCloudRepository = midWeatherCloudRepository;
        this.midWeatherRainRepository = midWeatherRainRepository;
        this.midWeatherTemperatureRepository = midWeatherTemperatureRepository;
        this.regionCodeRepository = regionCodeRepository;
    }

    public void createMidLandFcst() {
        LocalDate now = LocalDate.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        String beforeDate = now.minusDays(1L).format(DateTimeFormatter.BASIC_ISO_DATE);

        List<MidWeatherRain> beforeMidWeatherRains = midWeatherRainRepository.findAllByInquiryDate(beforeDate);
        List<MidWeatherCloud> beforeMidWeatherCloud = midWeatherCloudRepository.findAllByInquiryDate(beforeDate);

        for (MidWeatherRain midWeatherRain : beforeMidWeatherRains) {
            midWeatherRainRepository.save(makeMidWeatherRain(nowDate, midWeatherRain));
        }

        for (MidWeatherCloud midWeatherCloud : beforeMidWeatherCloud) {
            midWeatherCloudRepository.save(makeMidWeatherCloud(nowDate, midWeatherCloud));
        }
    }

    private MidWeatherRain makeMidWeatherRain(String nowDate, MidWeatherRain midWeatherRain) {
        return MidWeatherRain.builder()
                .regionCodeId(midWeatherRain.getRegionCodeId())
                .inquiryDate(nowDate)
                .rainFall0Am(midWeatherRain.getRainFall1Am())
                .rainFall0Pm(midWeatherRain.getRainFall1Pm())
                .rainFall1Am(midWeatherRain.getRainFall2Am())
                .rainFall1Pm(midWeatherRain.getRainFall2Pm())
                .rainFall2Am(midWeatherRain.getRainFall3Am())
                .rainFall2Pm(midWeatherRain.getRainFall3Pm())
                .rainFall3Am(midWeatherRain.getRainFall4Am())
                .rainFall3Pm(midWeatherRain.getRainFall4Pm())
                .rainFall4Am(midWeatherRain.getRainFall5Am())
                .rainFall4Pm(midWeatherRain.getRainFall5Pm())
                .rainFall5Am(midWeatherRain.getRainFall6Am())
                .rainFall5Pm(midWeatherRain.getRainFall6Pm())
                .rainFall6Am(midWeatherRain.getRainFall7Am())
                .rainFall6Pm(midWeatherRain.getRainFall7Pm())
                .rainFall7Am(midWeatherRain.getRainFall8())
                .rainFall7Pm(midWeatherRain.getRainFall8())
                .build();
    }

    private MidWeatherCloud makeMidWeatherCloud(String nowDate, MidWeatherCloud midWeatherCloud) {
        return MidWeatherCloud.builder()
                .regionCodeId(midWeatherCloud.getRegionCodeId())
                .inquiryDate(nowDate)
                .cloud0Am(midWeatherCloud.getCloud1Am())
                .cloud0Pm(midWeatherCloud.getCloud1Pm())
                .cloud1Am(midWeatherCloud.getCloud2Am())
                .cloud1Pm(midWeatherCloud.getCloud2Pm())
                .cloud2Am(midWeatherCloud.getCloud3Am())
                .cloud2Pm(midWeatherCloud.getCloud3Pm())
                .cloud3Am(midWeatherCloud.getCloud4Am())
                .cloud3Pm(midWeatherCloud.getCloud4Pm())
                .cloud4Am(midWeatherCloud.getCloud5Am())
                .cloud4Pm(midWeatherCloud.getCloud5Pm())
                .cloud5Am(midWeatherCloud.getCloud6Am())
                .cloud5Pm(midWeatherCloud.getCloud6Pm())
                .cloud6Am(midWeatherCloud.getCloud7Am())
                .cloud6Pm(midWeatherCloud.getCloud7Pm())
                .cloud7Am(midWeatherCloud.getCloud8())
                .cloud7Pm(midWeatherCloud.getCloud8())
                .build();
    }

    public void createMidTa() {
        LocalDate now = LocalDate.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        String beforeDate = now.minusDays(1L).format(DateTimeFormatter.BASIC_ISO_DATE);

        List<MidWeatherTemperature> midWeatherTemperatures = midWeatherTemperatureRepository.findAllByInquiryDate(beforeDate);

        for (MidWeatherTemperature midWeatherTemperature : midWeatherTemperatures) {
            midWeatherTemperatureRepository.save(makeMidWeatherTemperature(nowDate, midWeatherTemperature));
        }
    }

    private MidWeatherTemperature makeMidWeatherTemperature(String nowDate, MidWeatherTemperature midWeatherTemperature) {
        return MidWeatherTemperature.builder()
                .regionCodeId(midWeatherTemperature.getRegionCodeId())
                .inquiryDate(nowDate)
                .temperature0Min(midWeatherTemperature.getTemperature1Min())
                .temperature0Max(midWeatherTemperature.getTemperature1Max())
                .temperature1Min(midWeatherTemperature.getTemperature2Min())
                .temperature1Max(midWeatherTemperature.getTemperature2Max())
                .temperature2Min(midWeatherTemperature.getTemperature3Min())
                .temperature2Max(midWeatherTemperature.getTemperature3Max())
                .temperature3Min(midWeatherTemperature.getTemperature4Min())
                .temperature3Max(midWeatherTemperature.getTemperature4Max())
                .temperature4Min(midWeatherTemperature.getTemperature5Min())
                .temperature4Max(midWeatherTemperature.getTemperature5Max())
                .temperature5Min(midWeatherTemperature.getTemperature6Min())
                .temperature5Max(midWeatherTemperature.getTemperature6Max())
                .temperature6Min(midWeatherTemperature.getTemperature7Min())
                .temperature6Max(midWeatherTemperature.getTemperature7Max())
                .temperature7Min(midWeatherTemperature.getTemperature8Min())
                .temperature7Max(midWeatherTemperature.getTemperature8Max())
                .build();
    }

    public void updateMidLandFcst(String date, String time) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.LAND);
        MidWeatherRains midWeatherRains = new MidWeatherRains(midWeatherRainRepository.findAllByInquiryDate(date));
        MidWeatherClouds midWeatherClouds = new MidWeatherClouds(midWeatherCloudRepository.findAllByInquiryDate(date));

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), date + time);
            MidLandDto midLandDto = midWeatherApiService.requestMidLandFcst(uri);

            MidWeatherRain midWeatherRain = midWeatherRains.findMidWeatherRain(code.getId(), date);
            midWeatherRain.updateRain(midLandDto.rnSt4Am(), midLandDto.rnSt4Pm(), midLandDto.rnSt5Am(), midLandDto.rnSt5Pm(), midLandDto.rnSt6Am(), midLandDto.rnSt6Pm(), midLandDto.rnSt7Am(), midLandDto.rnSt7Pm(), midLandDto.rnSt8());

            MidWeatherCloud midWeatherCloud = midWeatherClouds.findMidWeatherCloud(code.getId(), date);
            midWeatherCloud.updateCloud(midLandDto.wf3Am(), midLandDto.wf3Pm(), midLandDto.wf4Am(), midLandDto.wf4Pm(), midLandDto.wf5Am(), midLandDto.wf5Pm(), midLandDto.wf6Am(), midLandDto.wf6Pm(), midLandDto.wf7Am(), midLandDto.wf7Pm(), midLandDto.wf8());
        }
    }

    public void updateMidTa(String date, String time) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.TEMP);
        MidWeatherTemperatures midWeatherTemperatures = new MidWeatherTemperatures(midWeatherTemperatureRepository.findAllByInquiryDate(date));

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), date + time);
            MidTemperatureDto midTemperatureDto = midWeatherApiService.requestMidTa(uri);

            MidWeatherTemperature midWeatherTemperature = midWeatherTemperatures.findMidWeatherTemperatures(code.getId(), date);
            midWeatherTemperature.updateTemperature(midTemperatureDto.taMin4(), midTemperatureDto.taMax4(), midTemperatureDto.taMin5(), midTemperatureDto.taMax5(), midTemperatureDto.taMin6(), midTemperatureDto.taMax6(), midTemperatureDto.taMin7(), midTemperatureDto.taMax7(), midTemperatureDto.taMin8(), midTemperatureDto.taMax8());
        }
    }
}
