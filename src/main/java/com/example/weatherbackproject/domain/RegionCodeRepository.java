package com.example.weatherbackproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionCodeRepository extends JpaRepository<RegionCode, Long> {

    Optional<RegionCode> findByTypeAndStateContaining(MidType type, String state);

    List<RegionCode> findAllByType(MidType type);
}
