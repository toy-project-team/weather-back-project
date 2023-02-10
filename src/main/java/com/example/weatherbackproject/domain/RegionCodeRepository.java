package com.example.weatherbackproject.domain;

import java.util.List;

public interface RegionCodeRepository {

    RegionCode save(RegionCode regionCode);

    List<RegionCode> findAllByType(MidType type);
}
