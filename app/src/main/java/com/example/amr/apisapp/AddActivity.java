package com.example.amr.apisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddActivity extends AppCompatActivity {

    EditText firstname, lastname, age;
    Button insert;
    RequestQueue requestQueue;
    String user_email;
    String insertUrl = "http://192.168.1.107/phpinandroid/insertStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstname = (EditText) findViewById(R.id.editText);
        lastname = (EditText) findViewById(R.id.editText2);
        age = (EditText) findViewById(R.id.editText3);
        insert = (Button) findViewById(R.id.insert);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        user_email = b.getString("usernamee");

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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
                        parameters.put("firstname", firstname.getText().toString());
                        parameters.put("lastname", lastname.getText().toString());
                        parameters.put("age", age.getText().toString());
                        parameters.put("username", user_email);

                        return parameters;
                    }
                };
                requestQueue.add(request);

                Toast.makeText(AddActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
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
