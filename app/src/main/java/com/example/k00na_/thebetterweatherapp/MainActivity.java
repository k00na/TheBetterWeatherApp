package com.example.k00na_.thebetterweatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.k00na_.thebetterweatherapp.Activities.AddCity;
import com.example.k00na_.thebetterweatherapp.Activities.CityData;
import com.example.k00na_.thebetterweatherapp.Model.WeatherData;
import com.example.k00na_.thebetterweatherapp.Util.WeatherDataJSONSerializer;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static List<WeatherData> weatherDataList = new ArrayList<WeatherData>();
    private TextView isThereData;
    private FloatingActionButton FAB;
    private static final int REQUEST_CITY = 11;
    private RecyclersAdapter mRecyclersAdapter;
    private static final String mFileName = "weatherdata.json";
    private WeatherDataJSONSerializer jsonSerializer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        try {
            weatherDataList = jsonSerializer.loadWeatherData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
        mRecyclersAdapter = new RecyclersAdapter(weatherDataList, this);


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewID);
        mRecyclerView.setAdapter(mRecyclersAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        isThereData = (TextView)findViewById(R.id.dataOrNotTextView);
        FAB = (FloatingActionButton)findViewById(R.id.fab);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddCity.class);
                startActivityForResult(i, REQUEST_CITY);

            }
        });
        /*
            getting JSON data
         */

        Intent i = getIntent();
        String JSONfromAddCity = i.getStringExtra("JSON");
        if(JSONfromAddCity != null){

            try {
                WeatherData weatherData = new WeatherData(JSONfromAddCity);
                weatherDataList.add(weatherData);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CITY){
            Log.i("checkList", "request = OK");
            if(resultCode == Activity.RESULT_OK){
                Log.i("checkList", "result = OK");
                String getJSON = data.getStringExtra("JSON");
                try {
                    WeatherData wd = new WeatherData(getJSON);
                    mRecyclersAdapter.addToList(wd, mRecyclersAdapter.getListSize());
                    Log.i("checkList", "List size is " + weatherDataList.size());
                    isThereData.setText(R.string.listOfCities);
                    jsonSerializer.saveWeatherData(mRecyclersAdapter.getAdaptersData());


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("checkList", "Shit went wrong: " + e);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    /*
                ADAPTER
         */
    class RecyclersAdapter extends RecyclerView.Adapter<RowData>{

        private LayoutInflater mLayoutInflater;
        private List<WeatherData> adaptersData = Collections.emptyList();

        public int getListSize(){
            return adaptersData.size();
        }

        public List<WeatherData> getAdaptersData() {
            return adaptersData;
        }

        public RecyclersAdapter(List<WeatherData> data, Context context){

            mLayoutInflater = LayoutInflater.from(context);
            adaptersData = data;

        }

        public void addToList(WeatherData wd, int pos){
            adaptersData.add(wd);
            notifyItemInserted(pos);
        }


        @Override
        public RowData onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = mLayoutInflater.inflate(R.layout.row_data, viewGroup, false);
            RowData rowData = new RowData(v);

            return rowData;
        }

        @Override
        public void onBindViewHolder(RowData rowData, int i) {

            rowData.cityDegrees.setText(adaptersData.get(i).getTemp() + " degrees");
            rowData.cityName.setText(adaptersData.get(i).getCityName());

        }

        @Override
        public int getItemCount() {
            return adaptersData.size();
        }
    }

    class RowData extends RecyclerView.ViewHolder{

        TextView cityName;
        TextView cityDegrees;

        public RowData(View itemView) {
            super(itemView);

            cityName = (TextView)itemView.findViewById(R.id.cityNameTextView);
            cityDegrees = (TextView)itemView.findViewById(R.id.cityDegreesTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WeatherData wd = weatherDataList.get(getAdapterPosition());

                    String cityN = wd.getCityName();
                    String cityTemp = "" + wd.getTemp();
                    String cityHum = "" + wd.getHumidty();
                    String cityDesc = wd.getDescription();
                    String[] data = {cityN, cityTemp, cityHum, cityDesc};

                    Intent i = new Intent(getApplicationContext(), CityData.class);
                    i.putExtra("dataArray", data);
                    startActivity(i);
                }
            });

        }


    }

    public static List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }
}
