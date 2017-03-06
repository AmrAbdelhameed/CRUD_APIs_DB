package com.example.amr.apisapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class DetailsActivity extends AppCompatActivity {

    TextView firstname, lastname, age;
    Button update;
    RequestQueue requestQueue;
    String a, aa, aaa, aaaa;
    String updateUrl = "http://192.168.1.107/phpinandroid/updateStudent.php";
    String deleteUrl = "http://192.168.1.107/phpinandroid/deleteStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstname = (TextView) findViewById(R.id.editTextt);
        lastname = (TextView) findViewById(R.id.editText22);
        age = (TextView) findViewById(R.id.editText33);
        update = (Button) findViewById(R.id.update);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        a = b.getString("ab");
        aa = b.getString("abab");
        aaa = b.getString("ababab");
        aaaa = b.getString("abababab");

        update.setVisibility(View.INVISIBLE);

        firstname.setText(aa);
        firstname.setFocusable(false);
        firstname.setClickable(false);
        firstname.setCursorVisible(false);

        lastname.setText(aaa);
        lastname.setFocusable(false);
        lastname.setClickable(false);
        lastname.setCursorVisible(false);

        age.setText(aaaa);
        age.setFocusable(false);
        age.setClickable(false);
        age.setCursorVisible(false);

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

                        parameters.put("id", a);
                        parameters.put("firstname", firstname.getText().toString());
                        parameters.put("lastname", lastname.getText().toString());
                        parameters.put("age", age.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

                Toast.makeText(DetailsActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        if (id == R.id.item2) {

            update.setVisibility(View.VISIBLE);

            firstname.setEnabled(true);
            firstname.setFocusableInTouchMode(true);
            firstname.setClickable(true);
            firstname.setCursorVisible(true);

            lastname.setEnabled(true);
            lastname.setFocusableInTouchMode(true);
            lastname.setClickable(true);
            lastname.setCursorVisible(true);

            age.setEnabled(true);
            age.setFocusableInTouchMode(true);
            age.setClickable(true);
            age.setCursorVisible(true);

            return true;
        }

        if (id == R.id.item3) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to delete all your data ?!")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            StringRequest request = new StringRequest(Request.Method.POST, deleteUrl, new Response.Listener<String>() {
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

                                    parameters.put("id", a);

                                    return parameters;
                                }
                            };
                            requestQueue.add(request);

                            Toast.makeText(DetailsActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Nothing
                }
            });
            AlertDialog d = builder.create();
            d.setTitle("Are you sure");
            d.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}