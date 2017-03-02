package com.example.amr.apisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText name, email, password;
    private Button sign_in_register;
    private RequestQueue requestQueue;
    private String insertUrl = "http://192.168.1.107/phpinandroid/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText) findViewById(R.id.txtNameRegistration);
        email = (EditText) findViewById(R.id.txtEmailRegistration);
        password = (EditText) findViewById(R.id.txtPasswordRegistration);
        sign_in_register = (Button) findViewById(R.id.buttonR);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        sign_in_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                         System.out.println(response.toString());

                        name.setText("");
                        email.setText("");
                        password.setText("");

                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("name", name.getText().toString());
                        parameters.put("email", email.getText().toString());
                        parameters.put("password", password.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
