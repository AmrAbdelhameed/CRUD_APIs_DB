package com.example.amr.apisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class Searching extends AppCompatActivity {

    EditText name;
    Button bTnSearch;
    public static final String JSON_URLL = "http://192.168.1.107/phpinandroid/searchdata.php";

    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText) findViewById(R.id.editText4);
        bTnSearch = (Button) findViewById(R.id.buttonS);
        listView2 = (ListView) findViewById(R.id.listView2);

        bTnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }

        });

    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URLL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Searching.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("firstname", name.getText().toString());

                return parameters;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.ids, ParseJSON.names, ParseJSON.emails, ParseJSON.ages);
        listView2.setAdapter(cl);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Intent intent = new Intent(Searching.this, DetailsActivity.class);
                String idd = ParseJSON.ids[position];
                String first = ParseJSON.names[position];
                String last = ParseJSON.emails[position];
                String age = ParseJSON.ages[position];

                Bundle b = new Bundle();

                b.putString("ab", idd);
                b.putString("abab", first);
                b.putString("ababab", last);
                b.putString("abababab", age);

                intent.putExtras(b);
                startActivity(intent);

            }

        });

        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {

                Toast.makeText(Searching.this, ParseJSON.ids[index], Toast.LENGTH_SHORT).show();

                return true;
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
