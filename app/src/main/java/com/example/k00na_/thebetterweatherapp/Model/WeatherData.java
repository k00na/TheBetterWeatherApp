package com.example.k00na_.thebetterweatherapp.Model;

import org.json.JSONArray;
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

    public WeatherData(){

    }


    public WeatherData(String jsonString) throws JSONException{

        JSONObject allData = new JSONObject(jsonString);

        JSONArray weatherArray = allData.getJSONArray("weather");
        JSONObject fromArrray = weatherArray.getJSONObject(0);
        description = fromArrray.getString("description");

        cityName = allData.getString("name");




        JSONObject main = allData.getJSONObject("main");

        temp = main.getDouble("temp");
        temp = (int) Math.round(temp - 273.15);


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
