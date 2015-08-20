package com.example.k00na_.thebetterweatherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.k00na_.thebetterweatherapp.Model.WeatherData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewID);


    }


    class RecyclersAdapter extends RecyclerView.Adapter<RowData>{

        private LayoutInflater mLayoutInflater;

        public RecyclersAdapter(List<WeatherData> data, Context context){



        }


        @Override
        public RowData onCreateViewHolder(ViewGroup viewGroup, int i) {


            return null;
        }

        @Override
        public void onBindViewHolder(RowData rowData, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
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
