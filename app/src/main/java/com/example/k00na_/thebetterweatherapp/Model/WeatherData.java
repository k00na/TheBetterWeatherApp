package com.example.k00na_.thebetterweatherapp.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by k00na_ on 20.8.2015.
 */
public class WeatherData {

    private String cityName;
    private String description;
    private double temp;
    private int humidty;


    public WeatherData(String jsonString) throws JSONException{

        JSONObject allData = new JSONObject(jsonString);

        cityName = allData.getString("name");

        JSONObject weather = allData.getJSONObject("weather");
        description = weather.getString("description");

        JSONObject main = allData.getJSONObject("main");

        temp = main.getDouble("temp");
        humidty = main.getInt("humidity");

    }

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
