package com.example.weatherbackproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryRegionCoordinateRepository implements RegionCoordinateRepository {
    private final Map<Long, RegionCoordinate> regionCoordinates = new HashMap<>();

    @Override
    public RegionCoordinate save(RegionCoordinate regionCoordinate) {
        regionCoordinates.put(regionCoordinate.getId(), regionCoordinate);
        return regionCoordinate;
    }

    @Override
    public List<RegionCoordinate> findAll() {
        return regionCoordinates.values().stream().toList();
    }

    @Override
    public Optional<RegionCoordinate> findByCityAndState(String city, String state) {
        return regionCoordinates.values().stream()
                .filter(regionCoordinate -> regionCoordinate.getCity().equals(city) && regionCoordinate.getState().equals(state))
                .findFirst();
    }
}
