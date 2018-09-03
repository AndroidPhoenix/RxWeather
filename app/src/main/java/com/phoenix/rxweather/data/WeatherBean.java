/**
  * Copyright 2018 bejson.com 
  */
package com.phoenix.rxweather.data;
import java.util.List;

/**
     {
     "coord": {
         "lon": 139,
         "lat": 35
     },
     "sys": {
         "country": "JP",
         "sunrise": 1369769524,
         "sunset": 1369821049
     },
     "weather": [{
         "id": 804,
         "main": "clouds",
         "description": "overcast clouds",
         "icon": "04n"
     }],
     "main": {
         "temp": 289.5,
         "humidity": 89,
         "pressure": 1013,
         "temp_min": 287.04,
         "temp_max": 292.04
     },
     "wind": {
         "speed": 7.31,
         "deg": 187.002
     },
     "rain": {
         "3h": 0
     },
     "clouds": {
         "all": 92
     },
     "dt": 1369824698,
     "id": 1851632,
     "name": "Shuzenji",
     "cod": 200
     }
 */
public class WeatherBean {

    private Coord coord;
    private Sys sys;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private long id;
    private String name;
    private int cod;
    public void setCoord(Coord coord) {
         this.coord = coord;
     }
     public Coord getCoord() {
         return coord;
     }

    public void setSys(Sys sys) {
         this.sys = sys;
     }
     public Sys getSys() {
         return sys;
     }

    public void setWeather(List<Weather> weather) {
         this.weather = weather;
     }
     public List<Weather> getWeather() {
         return weather;
     }

    public void setMain(Main main) {
         this.main = main;
     }
     public Main getMain() {
         return main;
     }

    public void setWind(Wind wind) {
         this.wind = wind;
     }
     public Wind getWind() {
         return wind;
     }

    public void setRain(Rain rain) {
         this.rain = rain;
     }
     public Rain getRain() {
         return rain;
     }

    public void setClouds(Clouds clouds) {
         this.clouds = clouds;
     }
     public Clouds getClouds() {
         return clouds;
     }

    public void setDt(long dt) {
         this.dt = dt;
     }
     public long getDt() {
         return dt;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setCod(int cod) {
         this.cod = cod;
     }
     public int getCod() {
         return cod;
     }

}