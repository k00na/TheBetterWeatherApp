package com.example.k00na_.thebetterweatherapp.Model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by k00na_ on 20.8.2015.
 */
public class DataSingleton {

    private static DataSingleton sDataSingleton;

    private ArrayList<WeatherData> mWeatherData;


    public static DataSingleton get(Context context){

        if(sDataSingleton == null){
            sDataSingleton = new DataSingleton(context);
        }

        return sDataSingleton;
    }

    private DataSingleton(Context context){



    }

}
