package com.example.weatherbackproject.infra;

import java.net.URI;

public interface WeatherApiClient<T> {

    T requestWeather(URI uri);
}
