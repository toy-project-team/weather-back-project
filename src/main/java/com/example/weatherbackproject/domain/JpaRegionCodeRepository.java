package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRegionCodeRepository extends RegionCodeRepository, JpaRepository<RegionCode, Long> {
}
