package com.example.joe.tank_monitor_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by root on 4/6/18.
 */

public class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart(){
        super.onStart();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intentMain);
                            case R.id.action_analytics:
                                Intent intentAnalytics = new Intent(getApplicationContext(), AnalyticsActivity.class);
                                startActivity(intentAnalytics);
                        }
                        return true;
                    }
                });

    }


}
