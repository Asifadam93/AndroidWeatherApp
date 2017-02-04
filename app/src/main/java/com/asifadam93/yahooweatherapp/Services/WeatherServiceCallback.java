package com.asifadam93.yahooweatherapp.Services;

import com.asifadam93.yahooweatherapp.Data.Channel;

/**
 * Created by Asifadam93 on 04/02/2017.
 */

public interface WeatherServiceCallback {

    void onSuccess(Channel channel);
    void onFail(Exception exception);

}
