package com.example.ui_left_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Volunteer_HomePage extends AppCompatActivity {
Button b1;
String loc_id;
static Volunteer_HomePage parentContext;
    ArrayList <String> allNames = new ArrayList<String>();
    ArrayList <String> allIds = new ArrayList<String>();

    AlertDialog.Builder builder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.volunteer_hompage);
        parentContext = Volunteer_HomePage.this;
        b1 = (Button) findViewById(R.id.button8);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Volunteer_HomePage.this,Volunteer_Login.class);
                startActivity(intent);
            }
        });


    Intent intent = getIntent();
     loc_id = intent.getStringExtra("loc_id");


     getAllEvents();

    }

    private void getAllEvents() {

        String server_url = "https://limitless-journey-75415.herokuapp.com/volunteer/" + loc_id;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            JSONArray cast = jsonResponse.getJSONArray("events");
                            for (int i=1; i<cast.length(); i++) {

                                JSONObject event = cast.getJSONObject(i);
                                String name = event.getString("Name");
                                String id = event.getString("_id");
                                allNames.add(name);
                                allIds.add(id);
                            }

                            RecyclerView recyclerView = findViewById(R.id.vol_recycler_view);
                            upComingEventAdapter adapter = new upComingEventAdapter(parentContext ,allNames,allIds,"volunteer");
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(parentContext));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Volunteer_HomePage.this,"Error While sending ",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                return params   ;
            }
        };

        MySingleTon.getInstance(Volunteer_HomePage.this).addTorequestque(stringRequest);

    }

    public void goToDescription(String id){
        Intent s2=new Intent(Volunteer_HomePage.this,event_discription.class);
        s2.putExtra("id" ,id);
        startActivity(s2);
    }

}
