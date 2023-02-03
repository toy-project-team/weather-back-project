package com.example.weatherbackproject.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.weatherbackproject.domain.QShortWeather.shortWeather;

public class ShortWeatherRepositoryImpl implements ShortWeatherRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ShortWeatherRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ShortWeather> findShortWeatherCurrentTime(LocalDateTime baseDate, Long regionCodeId) {
        return queryFactory
                .select(shortWeather)
                .from(shortWeather)
                .where(shortWeather.regionCodeId.eq(regionCodeId), shortWeather.inquiryDate.goe(baseDate))
                .orderBy(shortWeather.inquiryDate.asc())
                .fetch();
    }
}
