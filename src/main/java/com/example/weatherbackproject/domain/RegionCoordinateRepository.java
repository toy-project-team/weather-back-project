package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionCoordinateRepository extends JpaRepository<RegionCoordinate, Long> {

    Optional<RegionCoordinate> findByCityAndState(String city, String state);
}
