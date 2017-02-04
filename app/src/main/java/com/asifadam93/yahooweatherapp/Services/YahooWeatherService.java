package com.asifadam93.yahooweatherapp.Services;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.asifadam93.yahooweatherapp.Data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.WriteAbortedException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Asifadam93 on 04/02/2017.
 */

public class YahooWeatherService {

    private WeatherServiceCallback weatherServiceCallback;
    private Exception exception;

    public YahooWeatherService(WeatherServiceCallback weatherServiceCallback) {
        this.weatherServiceCallback = weatherServiceCallback;
    }

    public void refreshWeather(final String location) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                String query = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'",location);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(query));

                try {
                    URL url = new URL(endpoint);

                    URLConnection urlConnection = url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }

                    Log.i("weatherApi","Url : "+endpoint);

                    return stringBuilder.toString();

                } catch (Exception e) {
                    exception = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s == null && exception != null){
                    weatherServiceCallback.onFail(exception);
                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(s);

                    JSONObject jsonObjectQuery = jsonObject.optJSONObject("query");

                    if(jsonObjectQuery.optInt("count") == 0){
                        weatherServiceCallback.onFail(new WeatherServiceException("Couldn't find weather informations for "+location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.parse(jsonObjectQuery.optJSONObject("results").optJSONObject("channel"));

                    weatherServiceCallback.onSuccess(channel);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }.execute(location);
    }
}
