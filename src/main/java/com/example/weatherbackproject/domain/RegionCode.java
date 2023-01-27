package com.example.weatherbackproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RegionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MidType type;
    private String code;
    private String state;
    private double latitude;
    private double longitude;

    public RegionCode(MidType type, String code, String state, double latitude, double longitude) {
        this.type = type;
        this.code = code;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
