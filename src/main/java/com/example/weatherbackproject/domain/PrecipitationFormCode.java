package com.example.weatherbackproject.domain;

import lombok.Getter;

@Getter
public enum PrecipitationFormCode {
    ZERO(0, "없음"),
    ONE(1, "비"),
    TWO(2, "비/눈"),
    THIRD(3, "눈"),
    FOUR(4, "소나기");

    private int num;
    private String name;

    PrecipitationFormCode(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public static String searchNum(int num) {
        for (PrecipitationFormCode precipitationFormCode : PrecipitationFormCode.values()) {
            if (precipitationFormCode.num == num) {
                return precipitationFormCode.name;
            }
        }

        return "";
    }
}
