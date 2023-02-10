package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRegionCodeRepository extends RegionCodeRepository, JpaRepository<RegionCode, Long> {

    Optional<RegionCode> findByTypeAndStateContaining(MidType type, String state);
}
