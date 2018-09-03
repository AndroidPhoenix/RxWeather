package com.phoenix.rxweather.presenter;

import com.phoenix.rxweather.data.WeatherBean;

public interface IWeather {

    interface WeatherListener {
        void onWeatherAcquired(WeatherBean bean);
    }

    WeatherBean getetWeather();

    void asyncGetWeather(WeatherListener listener);
}
