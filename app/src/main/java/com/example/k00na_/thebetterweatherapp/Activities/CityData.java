package com.example.k00na_.thebetterweatherapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.k00na_.thebetterweatherapp.MainActivity;
import com.example.k00na_.thebetterweatherapp.Model.WeatherData;
import com.example.k00na_.thebetterweatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class CityData extends AppCompatActivity {

    private List<WeatherData> mWeatherDataList = new ArrayList<WeatherData>();
    private TextView cityName;
    private TextView description;
    private TextView temperature;
    private TextView humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_data);




        cityName = (TextView)findViewById(R.id.cityName);
        temperature = (TextView)findViewById(R.id.temperature);
        humidity = (TextView)findViewById(R.id.humidity);
        description = (TextView)findViewById(R.id.description);

        Intent i = getIntent();
        String[] data = i.getStringArrayExtra("dataArray");

        cityName.setText(data[0]);
        temperature.setText(data[1]);
        humidity.setText(data[2]);
        description.setText(data[3]);

/*
        mWeatherDataList = MainActivity.getWeatherDataList();
        WeatherData wd = mWeatherDataList.get(position);

        cityName.setText(wd.getCityName());
        temperature.setText(wd.getTemp() + " degrees");
        humidity.setText(wd.getHumidty());
        description.setText(wd.getDescription());

*/




    }


}
