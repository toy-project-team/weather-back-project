package com.example.weatherbackproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RegionCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;
    private int latitude;
    private int longitude;

    public RegionCoordinate(String state, int latitude, int longitude) {
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
