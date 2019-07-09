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

public class Volunteer_Login extends AppCompatActivity {
    Button button;
    EditText Name ,Emailr;
    String server_url = "https://limitless-journey-75415.herokuapp.com/volunteer/login";
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer__login);
        button = (Button) findViewById(R.id.button7);
        Name = (EditText) findViewById(R.id.editText3);
        Emailr = (EditText) findViewById(R.id.editText15);
        builder = new AlertDialog.Builder(Volunteer_Login.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,email;
                name = Name.getText().toString();

                email = Emailr.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject json = new JSONObject(response);
                                    String use = json.getString("Username");
                                    String password = json.getString("password");
                                    String loc_id = json.getString("id");

                                        Intent intent = new Intent(Volunteer_Login.this,Volunteer_HomePage.class);
                                        intent.putExtra("loc_id",loc_id);

                                        Toast.makeText(Volunteer_Login.this,"ThankYou for login as " ,Toast.LENGTH_LONG).show();
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Volunteer_Login.this,"Please fill your details Correctly",Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Username",name);
                        params.put("Password",email);
                        return params   ;
                    }
                };

                MySingleTon.getInstance(Volunteer_Login.this).addTorequestque(stringRequest);

            }
        });
    }

}
