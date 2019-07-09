package com.example.ui_left_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserType extends AppCompatActivity {

    Button signUP_food,login_food,signUp_Volunteer,login_volunteer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
    }

    public void signup_foodDonor(View view) {
        signUP_food=findViewById(R.id.button4);
        signUP_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(UserType.this,FoodDonor_SignUp.class);
                startActivity(intent1);
            }
        });
    }

    public void login_foodDonor(View view) {
    login_food=findViewById(R.id.button5);
    login_food.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent2=new Intent(UserType.this,FoodDonor_Login.class);
            startActivity(intent2);
        }
    });
    }

    public void signup_volunteer(View view) {
        signUp_Volunteer=findViewById(R.id.button3);
        signUp_Volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(UserType.this,Volunteer_SignUp.class);
                startActivity(intent3);
            }
        });
    }

    public void login_volunteer(View view) {
        signUp_Volunteer=findViewById(R.id.button2);
        signUp_Volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(UserType.this,Volunteer_Login.class);
            startActivity(intent4);
            }
        });
    }
}
