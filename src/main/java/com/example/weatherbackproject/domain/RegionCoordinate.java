package com.example.weatherbackproject.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RegionCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private int nx;
    private int ny;

    public RegionCoordinate(String city, String state, double latitude, double longitude, int nx, int ny) {
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nx = nx;
        this.ny = ny;
    }

    @Builder
    public RegionCoordinate(Long id, String city, String state, double latitude, double longitude, int nx, int ny) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nx = nx;
        this.ny = ny;
    }
}
