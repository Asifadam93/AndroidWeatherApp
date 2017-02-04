package com.asifadam93.yahooweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asifadam93.yahooweatherapp.Data.Channel;
import com.asifadam93.yahooweatherapp.Services.WeatherServiceCallback;
import com.asifadam93.yahooweatherapp.Services.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private YahooWeatherService yahooWeatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yahooWeatherService = new YahooWeatherService(this);
        yahooWeatherService.refreshWeather("chennai, in");
    }

    @Override
    public void onSuccess(Channel channel) {
        Log.i("weatherApi","onSuccess");
        Log.i("weatherApi",String.format("Temperature : %d °%s",channel.getItems().getCondition().getTemperature(),channel.getUnits().getTempUnit()));
    }

    @Override
    public void onFail(Exception exception) {
        Log.i("weatherApi","onFail : "+exception.getMessage());
    }
}
