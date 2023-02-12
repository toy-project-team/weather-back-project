package com.example.weatherbackproject.domain;

import java.util.List;
import java.util.Optional;

public interface RegionCodeRepository {

    RegionCode save(RegionCode regionCode);

    List<RegionCode> findAllByType(MidType type);

    Optional<RegionCode> findByTypeAndStateContaining(MidType type, String state);
}
