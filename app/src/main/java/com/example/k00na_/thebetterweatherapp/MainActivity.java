package com.example.k00na_.thebetterweatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.k00na_.thebetterweatherapp.Activities.AddCity;
import com.example.k00na_.thebetterweatherapp.Model.WeatherData;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<WeatherData> weatherData;
    private TextView isThereData;
    private FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewID);
        isThereData = (TextView)findViewById(R.id.dataOrNotTextView);
        FAB = (FloatingActionButton)findViewById(R.id.fab);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddCity.class);
                startActivity(i);

            }
        });




    }


    /*
            ADAPTER
     */
    class RecyclersAdapter extends RecyclerView.Adapter<RowData>{

        private LayoutInflater mLayoutInflater;
        private List<WeatherData> adaptersData = Collections.emptyList();

        public RecyclersAdapter(List<WeatherData> data, Context context){

            mLayoutInflater = LayoutInflater.from(context);
            adaptersData = data;

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

        }
    }





}
