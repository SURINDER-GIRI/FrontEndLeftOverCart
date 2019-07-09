package com.example.ui_left_final;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class event_discription extends AppCompatActivity {
Button backhome;
TextView Name,StartDate,EndDate,StartTime,EndTime,StreetNo,
        PostalCode,City,Province,Country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_discription);
        Name = (TextView) findViewById(R.id.textView9);
        StartDate = (TextView) findViewById(R.id.textView10);
        EndDate = (TextView) findViewById(R.id.textView11);
        StartTime = (TextView)  findViewById(R.id.textView12);
        EndTime = (TextView) findViewById(R.id.textView13);
        StreetNo = (TextView) findViewById(R.id.textView14);
        PostalCode = (TextView) findViewById(R.id.textView15);
        City = (TextView) findViewById(R.id.textView16);
        Province = (TextView) findViewById(R.id.textView17);
        Country = (TextView) findViewById(R.id.textView18);

        Intent intent = getIntent();
        String Eventid = intent.getStringExtra("id");

        String server_url = "https://limitless-journey-75415.herokuapp.com/events/description/" + Eventid;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);
                            JSONObject obj = json.getJSONObject("event");
                            JSONObject obj1 = json.getJSONObject("location");
                            String name = obj.getString("Name");
                            String startTime = obj.getString("StartTime");
                            String endTime = obj.getString("EndTime");
                            String startDate = obj.getString("StartDate");
                            String endDate = obj.getString("EndDate");
                            String streetNo = obj1.getString("Street_name");
                            String postalCode = obj1.getString("PostalCode");
                            String city = obj1.getString("City");
                            String province = obj1.getString("Province");
                            String country = obj1.getString("Country");
                            Name.setText("Name :  " + name);
                            StartTime.setText("Start Time : " +startTime);
                            EndTime.setText("End Time :  " +endTime);
                            StartDate.setText("Start Date : " +startDate);
                            EndDate.setText("End Date  : " +endDate);
                            StreetNo.setText("Street Name : " + streetNo);
                            City.setText("City :" + city);
                            PostalCode.setText("Postal Code" + postalCode);
                            Province.setText("Province " + province);
                            Country.setText("Country " + country);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(event_discription.this,"Error While sending ",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                return params   ;
            }
        };

        MySingleTon.getInstance(event_discription.this).addTorequestque(stringRequest);


    }

    public void btn_backtohome(View view) {
    backhome=findViewById(R.id.button6_backhomea);
    backhome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(event_discription.this,Create_Event.class);
            startActivity(intent);
        }
    });
    }

}
