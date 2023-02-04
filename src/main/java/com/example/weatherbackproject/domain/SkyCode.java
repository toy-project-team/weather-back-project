package com.example.weatherbackproject.domain;

import lombok.Getter;

@Getter
public enum SkyCode {
    ONE(1, "맑음"),
    THIRD(3, "구름많음"),
    FOUR(4, "흐림");

    private int num;
    private String sky;

    SkyCode(int num, String sky) {
        this.num = num;
        this.sky = sky;
    }

    public static String searchNum(int num) {
        for (SkyCode skyCode : SkyCode.values()) {
            if (skyCode.num == num) {
                return skyCode.sky;
            }
        }

        return "";
    }
}
