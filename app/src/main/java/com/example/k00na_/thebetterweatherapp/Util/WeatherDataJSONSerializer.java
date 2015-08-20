package com.example.k00na_.thebetterweatherapp.Util;

import android.content.Context;

import com.example.k00na_.thebetterweatherapp.Model.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

/**
 * Created by k00na_ on 20.8.2015.
 */
public class WeatherDataJSONSerializer {

    private Context mContext;
    private String mFileName;


    public WeatherDataJSONSerializer(Context cont, String fName){

        mContext = cont;
        mFileName = fName;

    }

    public void saveWeatherData(List<WeatherData> wData) throws JSONException, IOException{

        JSONArray jsonArray = new JSONArray();
        for(WeatherData wd : wData){
            jsonArray.put(wd.toJSON());

            Writer writer = null;

            try {
                OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
                writer = new OutputStreamWriter(out);
                writer.write(jsonArray.toString());
            }
            finally {
                if(writer != null)
                    writer.close();
            }
        }

    }

    public List<WeatherData> loadWeatherData() throws IOException, JSONException{

        List<WeatherData> wData = Collections.emptyList();

        BufferedReader reader = null;
        try{

            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null){

                jsonString.append(line);

            }

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for(int i = 0; i<array.length(); i++){
                wData.add(new WeatherData(array.getJSONObject(i)));
            }


        }catch (FileNotFoundException e){

        }finally {
            if(reader!=null)
                reader.close();
        }

        return wData;

    }


}
