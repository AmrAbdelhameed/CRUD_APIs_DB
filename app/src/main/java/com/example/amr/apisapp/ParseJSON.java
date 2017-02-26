package com.example.amr.apisapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Amr on 26/02/2017.
 */
public class ParseJSON {
    public static String[] ids;
    public static String[] names;
    public static String[] emails;
    public static String[] ages;

    public static final String JSON_ARRAY = "results";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "firstname";
    public static final String KEY_EMAIL = "lastname";
    public static final String KEY_AGE = "age";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            names = new String[users.length()];
            emails = new String[users.length()];
            ages = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                names[i] = jo.getString(KEY_NAME);
                emails[i] = jo.getString(KEY_EMAIL);
                ages[i] = jo.getString(KEY_AGE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}