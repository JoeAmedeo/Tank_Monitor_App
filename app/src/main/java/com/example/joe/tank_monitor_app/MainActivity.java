package com.example.joe.tank_monitor_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ApiCalls apiCalls = new ApiCalls();
    ArrayList<InputModel> sensorData1 = new ArrayList<>();
    ArrayList<InputModel> sensorData2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    protected void onStart(){
        super.onStart();
        try {
            sensorData1 = apiCalls.execute("17", "1").get();
            sensorData2 = apiCalls.execute("18", "1").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Set view textcd according to queried data
        TextView sensorTempurature1 = (TextView) findViewById(R.id.tank1TempValue);
        TextView sensorHumidity1 = (TextView) findViewById(R.id.tank1HumidValue);

        TextView sensorTempurature2 = (TextView) findViewById(R.id.tank2TempValue);
        TextView sensorHumidity2 = (TextView) findViewById(R.id.tank2HumidValue);

        sensorTempurature1.setText(sensorData1.get(0).tempurature);
        sensorHumidity1.setText(sensorData1.get(0).humidity);

        sensorTempurature2.setText(sensorData2.get(0).tempurature);
        sensorHumidity2.setText(sensorData2.get(0).humidity);
    }
}
