package com.asifadam93.yahooweatherapp.Data;

import org.json.JSONObject;

/**
 * Created by Asifadam93 on 04/02/2017.
 */

public class Condition implements JsonParseur {

    private int code;
    private int temperature;

    @Override
    public void parse(JSONObject jsonObject) {

        code = jsonObject.optInt("code");
        temperature = jsonObject.optInt("temp");

    }

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }
}
