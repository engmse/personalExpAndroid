package com.vpc3.personalexpensesapp.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.api.ApiClient;
import com.vpc3.personalexpensesapp.api.ApiInterface;
import com.vpc3.personalexpensesapp.api.reponse.LoginResponse;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText uname,upassword;
    CircularProgressButton login;
    Button register;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.startAnimation();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("KEY_UN",uname.getText().toString());
                editor.putString("KEY_PASS",upassword.getText().toString()) ;
                editor.apply();
                login(uname.getText().toString(),upassword.getText().toString()) ;
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

    private void login(String userName,String password){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> loginResponseCall=apiInterface.login(userName,password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                boolean opreation = response.body().getOper();
                String  msg = response.body().getMsg();
                if(opreation){
                    Intent i = new Intent(MainActivity.this, ExpensesActivity.class);
                    i.putExtra("KEY_UN",uname.getText().toString());
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                login.revertAnimation();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                login.revertAnimation();
            }
        });
    }
}