package com.example.ui_left_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Create_Event extends AppCompatActivity {
    Button button;
    String name,startdate,enddate,country,province,
            City,postalCode,streetName,starttime,endtime;
    EditText Name,StartDate,EndDate,StartTime,EndTime,Country,Province,
            city,PostalCode,StreetName;
    String server_url = "https://limitless-journey-75415.herokuapp.com/events/create";
    AlertDialog.Builder builder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);


        Name = findViewById(R.id.editText_cname);
        StartTime = findViewById(R.id.editText2_startTime);
        EndTime = findViewById(R.id.editText15_endTime);
        StartDate = findViewById(R.id.editText4_cstartDate);
        EndDate = findViewById(R.id.editText14_enddate);
        Country = findViewById(R.id.editText13_ccountry);
        Province = findViewById(R.id.editText12_cprovince);
        city = findViewById(R.id.editText10_ccity);
        PostalCode = findViewById(R.id.editText11_cpostalcode);
        StreetName = findViewById(R.id.editText9_cstreet_name);
        button = (Button) findViewById(R.id.button6_CreateEvent);
        builder1 = new AlertDialog.Builder(Create_Event.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                name = Name.getText().toString();
                startdate = StartDate.getText().toString();
                enddate = EndDate.getText().toString();
                country = Country.getText().toString();
                province = Province.getText().toString();
                City = city.getText().toString();
                postalCode = PostalCode.getText().toString();
                streetName = StreetName.getText().toString();
                starttime = StartTime.getText().toString();
                endtime= EndTime.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {


                                    builder1.setTitle("Tahnk You for Sign Up");
                                    builder1.setMessage("You Signed Up as");
                                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            Name.setText("");
                                            Intent intent=new Intent(Create_Event.this,FoodDonor_HomePage.class);
                                            startActivity(intent);

                                        }


                                    });

                                    AlertDialog alertDialog = builder1.create();
                                    alertDialog.show();

                                } catch (Error e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Create_Event.this,"Please fill your details Correctly",Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("Name",name);
                        params.put("StartTime",starttime);
                        params.put("EndTime",endtime);
                        params.put("StartDate",startdate);
                        params.put("EndDate",enddate);
                        params.put("Street_name",streetName);
                        params.put("City",City);
                        params.put("PostalCode",postalCode);
                        params.put("Province",province);
                        params.put("Country",country);

                        return params   ;
                    }
                };

                MySingleTon.getInstance(Create_Event.this).addTorequestque(stringRequest);

            }
        });

    }



}
