package com.phoenix.rxweather.net;


import com.phoenix.rxweather.data.WeatherBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiWeather {

    @GET("/data/2.5/weather")
    Observable<WeatherBean> getWeather(@Query("q") String city, @Query("APPID") String appid);
}
