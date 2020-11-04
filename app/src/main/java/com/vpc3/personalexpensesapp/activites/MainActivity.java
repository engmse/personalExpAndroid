package com.vpc3.personalexpensesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vpc3.personalexpensesapp.R;

public class MainActivity extends AppCompatActivity {

    EditText uname,upassword;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ExpensesActivty.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });

    }


    private void initViews(){
        uname = findViewById(R.id.userNameEt);
        upassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
    }
}