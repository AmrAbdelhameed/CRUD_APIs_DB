package com.example.amr.apisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {

    public static final String JSON_URL = "http://192.168.1.107/phpinandroid/datauser.php";
    TextView name;
    Button update;
    String email,id;
    RequestQueue requestQueue;
    String updateUrl = "http://192.168.1.107/phpinandroid/updateProfile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.editname);
        update = (Button) findViewById(R.id.update);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        email = b.getString("useremail");

        sendRequest();
    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("email", email);

                return parameters;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        GetCurrentUserData pj = new GetCurrentUserData(json);
        pj.GetCurrentData();

        update.setVisibility(View.INVISIBLE);

        id = pj.idds[0];

        name.setText(pj.namees[0]);
        name.setFocusable(false);
        name.setClickable(false);
        name.setCursorVisible(false);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();

                        parameters.put("id", id);
                        parameters.put("name", name.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

                Toast.makeText(UpdateProfile.this, "Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.itemUp)
        {
            update.setVisibility(View.VISIBLE);

            name.setEnabled(true);
            name.setFocusableInTouchMode(true);
            name.setClickable(true);
            name.setCursorVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
