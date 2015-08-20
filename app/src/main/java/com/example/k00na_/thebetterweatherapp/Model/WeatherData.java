package com.example.k00na_.thebetterweatherapp.Model;

/**
 * Created by k00na_ on 20.8.2015.
 */
public class WeatherData {

    private String cityName;
    private String description;
    private double temp;
    private int humidty;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidty() {
        return humidty;
    }

    public void setHumidty(int humidty) {
        this.humidty = humidty;
    }
}
