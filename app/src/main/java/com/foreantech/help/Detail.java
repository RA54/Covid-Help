package com.foreantech.help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.foreantech.help.Adapter.Detail_Adapter;
import com.foreantech.help.Model.Detail_model;

import org.json.*;
import java.util.*;

public class Detail extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Detail_model> obj_sootradhar;
    public String JSON_URL;
    private Detail_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        JSON_URL = getIntent().getStringExtra("de_id");
        recyclerView = findViewById(R.id.my_recycler_view);
        obj_sootradhar = new ArrayList<>();
        new extractApi().execute(" ");

        TextView textView = (TextView) findViewById(R.id.detial_titlle);
        textView.setText(getIntent().getStringExtra("detial_tittle"));

        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private class extractApi extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestQueue queue = Volley.newRequestQueue(Detail.this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://15.206.200.235:8000/serviceProvider/"+JSON_URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("Hello",""+response);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject songObject = response.getJSONObject(i);

                            String title = songObject.getString("title");
                            Log.d("yoyo","9871"+title);
                            String image = songObject.getString("image");
                            String url = songObject.getString("url");
                            obj_sootradhar.add(new Detail_model(title,image,url));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter = new Detail_Adapter(getApplicationContext(), obj_sootradhar);
                    recyclerView.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("tag", "onErrorResponse: " + error.getMessage());
                }
            });

            queue.add(jsonArrayRequest);
            return "ok";
        }
    }
}
