package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRegionCoordinateRepository extends RegionCoordinateRepository, JpaRepository<RegionCoordinate, Long> {
}
