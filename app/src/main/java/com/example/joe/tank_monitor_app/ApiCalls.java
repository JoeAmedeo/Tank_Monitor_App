package com.example.joe.tank_monitor_app;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApiCalls extends AsyncTask<String, String, ArrayList<InputModel>> {

    public ApiCalls(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<InputModel> doInBackground(String... strings) {
        OutputStream outputStream = null;
        OutputModel output = new OutputModel();
        output.sensorNumber = strings[0];
        output.rowCount = strings[1];
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson outputGson = gsonBuilder.create();
        String outputJson = outputGson.toJson(output);

        StringBuilder completeString = new StringBuilder();
        String line;

        ArrayList<InputModel> inputJsonList = new ArrayList<InputModel>();
        Type listType = new TypeToken<ArrayList<InputModel>>(){}.getType();

        try{
            URL url = new URL("http://calm-anchorage-24091.herokuapp.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            outputStream = new BufferedOutputStream(urlConnection.getOutputStream());



            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(outputJson);
            bufferedWriter.flush();
            bufferedWriter.close();

            urlConnection.connect();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while ((line = bufferedReader.readLine()) != null)
            {
                completeString.append(line);
            }

            inputJsonList = new Gson().fromJson(completeString.toString(), listType);

        }catch(IOException e){
            System.out.println(e.getStackTrace());
        }

        return inputJsonList;
    }
}
