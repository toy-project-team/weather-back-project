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
                .build();
    }

    public void updateSixMidLandFcst(String date) {
        updateMidLandFcst(date, date + "0600");
    }

    public void updateEighteenMidLandFcst(String date) {
        updateMidLandFcst(date, date + "1800");
    }

    public void updateMidLandFcst(String date, String dateTime) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.LAND);
        List<MidWeatherRain> midWeatherRains = midWeatherRainRepository.findAllByInquiryDate(date);
        List<MidWeatherCloud> midWeatherClouds = midWeatherCloudRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByLandFcst(code.getCode(), dateTime);

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

    public void updateSixMidTa(String date) {
        updateMidTa(date, date + "0600");
    }

    public void updateEighteenMidTa(String date) {
        updateMidTa(date, date + "1800");
    }

    public void updateMidTa(String date, String dateTime) {
        List<RegionCode> regionCodes = regionCodeRepository.findAllByType(MidType.TEMP);
        List<MidWeatherTemperature> midWeatherTemperatures = midWeatherTemperatureRepository.findAllByInquiryDate(date);

        for (RegionCode code : regionCodes) {
            URI uri = midWeatherUriBuilderService.buildUriByTa(code.getCode(), dateTime);

            MidTemperatureDto midTemperatureDto = midWeatherApiService.requestMidTa(uri);

            MidWeatherTemperature midWeatherTemperature = midWeatherTemperatures.stream()
                    .filter(tem -> tem.equalsCodeAndDate(code.getId(), date))
                    .findFirst()
                    .orElseThrow();

            midWeatherTemperature.updateTemperature(midTemperatureDto.taMin4(), midTemperatureDto.taMax4(), midTemperatureDto.taMin5(), midTemperatureDto.taMax5(), midTemperatureDto.taMin6(), midTemperatureDto.taMax6(), midTemperatureDto.taMin7(), midTemperatureDto.taMax7());
        }
    }
}
