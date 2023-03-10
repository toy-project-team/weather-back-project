package com.example.weatherbackproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryRegionCodeRepository implements RegionCodeRepository {
    private final Map<Long, RegionCode> regionCodes = new HashMap<>();

    @Override
    public RegionCode save(RegionCode regionCode) {
        regionCodes.put(regionCode.getId(), regionCode);
        return regionCode;
    }

    @Override
    public List<RegionCode> findAllByType(MidType type) {
        return regionCodes.values().stream()
                .filter(regionCode -> regionCode.getType().equals(type))
                .toList();
    }

    @Override
    public Optional<RegionCode> findByTypeAndStateContaining(MidType type, String state) {
        return regionCodes.values().stream()
                .filter(regionCode -> regionCode.getType().equals(type) && regionCode.getState().equals(state))
                .findFirst();
    }
}
