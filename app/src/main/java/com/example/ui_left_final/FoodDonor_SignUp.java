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
import java.util.jar.Attributes;

public class FoodDonor_SignUp extends AppCompatActivity {
Button button;
String username,passwrd,cnfirmPassword,name,contact;
EditText Username,Password,ConfirmPassword,Name,Contact;
    String server_url = "https://limitless-journey-75415.herokuapp.com/foodDonor/signUp";
    AlertDialog.Builder builder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_donor__sign_up);
        Username= (EditText)findViewById(R.id.editText_username);
        Password= (EditText)findViewById(R.id.editText4_password);
        ConfirmPassword= (EditText)findViewById(R.id.editText14_confirmPassword);
        Name=(EditText)findViewById(R.id.editText2_name);
        Contact=(EditText)findViewById(R.id.editText15_contact);

        button = (Button) findViewById(R.id.button6_login_food);
        builder1 = new AlertDialog.Builder(FoodDonor_SignUp.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               username = Username.getText().toString();
               passwrd = Password.getText().toString();
               cnfirmPassword =  ConfirmPassword.getText().toString();
               name = Name.getText().toString();
               contact = Contact.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject json = new JSONObject(response);

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject jo = jsonObject.getJSONObject("savedData");
                                    String usrnam = jo.getString("Username");
                                    String pass = jo.getString("Password");


                                    builder1.setTitle("Tahnk You for Sign Up");
                                    builder1.setMessage("You Signed Up as  " +usrnam +"and your Pasword is  " + passwrd );
                                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            Name.setText("");
                                            Intent intent=new Intent(FoodDonor_SignUp.this,Volunteer_Login.class);
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

                        Toast.makeText(FoodDonor_SignUp.this,"Please fill your details Correctly",Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Username",username);
                        params.put("Password",passwrd);
                        params.put("Name",name);
                        params.put("Contact",contact);


                        return params   ;
                    }
                };

                MySingleTon.getInstance(FoodDonor_SignUp.this).addTorequestque(stringRequest);

            }
        });

    }
}
