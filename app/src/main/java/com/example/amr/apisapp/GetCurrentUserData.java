package com.example.amr.apisapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Amr on 06/03/2017.
 */
public class GetCurrentUserData {

    public static String[] idds;
    public static String[] namees;
    public static String[] emaills;

    public static final String JSON_ARRAY = "results";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    private JSONArray users = null;
    private String json;

    public GetCurrentUserData(String json) {
        this.json = json;
    }

    protected void GetCurrentData() {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            idds = new String[users.length()];
            namees = new String[users.length()];
            emaills = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                idds[i] = jo.getString(KEY_ID);
                namees[i] = jo.getString(KEY_NAME);
                emaills[i] = jo.getString(KEY_EMAIL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
