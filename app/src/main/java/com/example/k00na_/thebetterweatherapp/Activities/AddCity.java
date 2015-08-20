package com.example.k00na_.thebetterweatherapp.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.k00na_.thebetterweatherapp.MainActivity;
import com.example.k00na_.thebetterweatherapp.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AddCity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText mEditText;
    private Button mSubmitButton;
    private static String basicUrl = "http://api.openweathermap.org/data/2.5/weather?q=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        mEditText = (EditText)findViewById(R.id.addCityEditText);
        mSubmitButton = (Button)findViewById(R.id.addCityButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cityText = mEditText.getText().toString();
                if(cityText.length() > 0)
                    getCityData(cityText);
                else
                    Toast.makeText(getApplicationContext(), "Enter the shitty city already!", Toast.LENGTH_LONG).show();



            }
        });


        mToolbar = (Toolbar)findViewById(R.id.toolbarXMLid);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.home){
            NavUtils.navigateUpTo(getParent(), getParentActivityIntent());
        }

        return super.onOptionsItemSelected(item);
    }

    public void getCityData(String cityName){

        String url = basicUrl + cityName;

        if(isNetworkAvailable()){

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                    Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onResponse(Response response) throws IOException {

                    final String jsonFromWeb = response.body().string();

                    if (response.isSuccessful()) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "" + jsonFromWeb, Toast.LENGTH_LONG).show();
                            }
                        });


                    }

                }
            });

        }
        else
            Toast.makeText(this, "No connection found...", Toast.LENGTH_LONG);

    }

    private boolean isNetworkAvailable(){

        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected())
            isAvailable = true;

        return isAvailable;


    }



}
