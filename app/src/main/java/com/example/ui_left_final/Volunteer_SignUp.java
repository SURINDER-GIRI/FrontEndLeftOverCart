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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Volunteer_SignUp extends AppCompatActivity {
    String username,passwrd,cnfirmPassword,name,startdate,enddate,country,province,
            City,postalCode,streetName,starttime,endtime;

    Button button;
    EditText Username,password,confrimpassword,Name,StartDate,EndDate,StartTime,EndTime,Country,Province,
            city,PostalCode,StreetName;
    String server_url = "https://limitless-journey-75415.herokuapp.com/volunteer/signUp";
    AlertDialog.Builder builder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer__sign_up);

            Username = findViewById(R.id.editText);
            password = findViewById(R.id.editText4);
            confrimpassword = findViewById(R.id.editText14);
            Name = findViewById(R.id.editText2);
            StartTime = findViewById(R.id.editText6);
            EndTime = findViewById(R.id.editText7);
            StartDate = findViewById(R.id.editText8);
            EndDate = findViewById(R.id.EndDate);
            Country = findViewById(R.id.editText9);
            Province = findViewById(R.id.editText10);
            city = findViewById(R.id.editText11);
            PostalCode = findViewById(R.id.editText12);
            StreetName = findViewById(R.id.editText13);
            button = (Button) findViewById(R.id.button6);
            builder1 = new AlertDialog.Builder(Volunteer_SignUp.this);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    username = Username.getText().toString();
                    passwrd = password.getText().toString();
                    cnfirmPassword = confrimpassword.getText().toString();
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
                                        JSONObject json = new JSONObject(response);

                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONObject jo = jsonObject.getJSONObject("result");
                                        String usrnam = jo.getString("Username");
                                        String pass = jo.getString("Password");


                                            builder1.setTitle("Tahnk You for Sign Up");
                                            builder1.setMessage("You Signed Up as  " +usrnam +"and your Pasword is " + passwrd );
                                            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    Name.setText("");
                                                    Intent intent=new Intent(Volunteer_SignUp.this,Volunteer_Login.class);
                                                    startActivity(intent);

                                                }


                                            });

                                            AlertDialog alertDialog = builder1.create();
                                            alertDialog.show();

                                    } catch (Error e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Volunteer_SignUp.this,"Please fill your details Correctly",Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }){
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("Username",username);
                            params.put("Password",passwrd);
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

                    MySingleTon.getInstance(Volunteer_SignUp.this).addTorequestque(stringRequest);

                }
            });
    }
}
