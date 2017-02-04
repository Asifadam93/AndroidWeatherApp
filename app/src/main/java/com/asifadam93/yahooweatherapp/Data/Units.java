package com.asifadam93.yahooweatherapp.Data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Asifadam93 on 04/02/2017.
 */

public class Units implements JsonParseur {

    private String tempUnit;

    @Override
    public void parse(JSONObject jsonObject) {

        try {
            tempUnit = jsonObject.getString("temperature");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getTempUnit() {
        return tempUnit;
    }
}
