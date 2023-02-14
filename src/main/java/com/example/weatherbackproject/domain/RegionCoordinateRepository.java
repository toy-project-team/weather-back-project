package com.example.weatherbackproject.domain;

import java.util.List;
import java.util.Optional;

public interface RegionCoordinateRepository {

    RegionCoordinate save(RegionCoordinate regionCoordinate);

    List<RegionCoordinate> findAll();

    Optional<RegionCoordinate> findByCityAndState(String city, String state);
}
