package com.foreantech.help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.foreantech.help.Adapter.Adapter_Main;
import com.foreantech.help.Model.Model_Main;

import org.json.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Model_Main> obj_product;
    private static String JSON_URL = "http://15.206.200.235:8000/serviceCategories/";
    private Adapter_Main adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyleview);
        obj_product = new ArrayList<>();
        extractApi();

    }

    private void extractApi()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               Log.d("Hello",""+response);
                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);
                        String id = songObject.getString("id");
                        String title = songObject.getString("name");
                        String image = songObject.getString("image");

                        obj_product.add(new Model_Main(id,title,image));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new Adapter_Main(getApplicationContext(), obj_product);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }
}
