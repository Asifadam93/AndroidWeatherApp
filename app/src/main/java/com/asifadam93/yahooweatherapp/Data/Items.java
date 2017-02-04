package com.asifadam93.yahooweatherapp.Data;

import org.json.JSONObject;

/**
 * Created by Asifadam93 on 04/02/2017.
 */

public class Items implements JsonParseur {

    private Condition condition;

    @Override
    public void parse(JSONObject jsonObject) {

        condition = new Condition();
        condition.parse(jsonObject.optJSONObject("condition"));

    }

    public Condition getCondition() {
        return condition;
    }
}
