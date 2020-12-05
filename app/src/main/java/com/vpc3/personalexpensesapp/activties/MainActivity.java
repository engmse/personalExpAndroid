package com.vpc3.personalexpensesapp.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vpc3.personalexpensesapp.R;

public class MainActivity extends AppCompatActivity {

    EditText uname,upassword;
    Button login,register;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("KEY_UN",uname.getText().toString());
                editor.putString("KEY_PASS",upassword.getText().toString()) ;
                editor.apply();
                Intent i = new Intent(MainActivity.this, ExpensesActivity.class);
                i.putExtra("KEY_UN",uname.getText().toString());
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
        preferences = getSharedPreferences("PREF_SETTINGS",MODE_PRIVATE);
        uname = findViewById(R.id.userNameEt);
        upassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        getPrefData();
    }


    private  void getPrefData(){
        uname.setText(preferences.getString("KEY_UN",null));
        upassword.setText(preferences.getString("KEY_PASS",null));
    }
}