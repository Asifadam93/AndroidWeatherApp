package com.asifadam93.yahooweatherapp.Data;

import org.json.JSONObject;

/**
 * Created by Asifadam93 on 04/02/2017.
 */

public class Channel implements JsonParseur {

    private Units units;
    private Items items;

    @Override
    public void parse(JSONObject jsonObject) {

        units = new Units();
        units.parse(jsonObject.optJSONObject("units"));

        items = new Items();
        items.parse(jsonObject.optJSONObject("item"));
    }

    public Units getUnits() {
        return units;
    }

    public Items getItems() {
        return items;
    }
}
