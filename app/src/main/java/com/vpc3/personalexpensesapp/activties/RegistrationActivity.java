package com.vpc3.personalexpensesapp.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.api.ApiClient;
import com.vpc3.personalexpensesapp.api.ApiInterface;
import com.vpc3.personalexpensesapp.api.reponse.RegestrationResponse;
import com.vpc3.personalexpensesapp.api.request.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText userName, password, cPassword, mobile;
    Button registerBtn;
    ProgressBar progressLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();

    }

    public void initView() {
        View v = findViewById(R.id.bar);
        ImageView back = v.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userName = findViewById(R.id.userNameEt);
        password = findViewById(R.id.userPassword);
        cPassword = findViewById(R.id.userCPassword);
        mobile = findViewById(R.id.mobileNo);
        registerBtn = findViewById(R.id.registerBtn);
        progressLoading = findViewById(R.id.progressLoading);
        registerBtn.setOnClickListener(view -> {
            progressLoading.setVisibility(View.VISIBLE);
            User user = new User();
            user.setUsername(userName.getText().toString());
            user.setPassword(password.getText().toString());
            user.setConpassword(cPassword.getText().toString());
            user.setMobile(mobile.getText().toString());
            user.setEmail("");
            registerUser(user);
        });
    }

    private void registerUser(User user) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<RegestrationResponse> callReigster = apiInterface.register(user.getUsername(),
                user.getMobile(),
                user.getPassword(),
                user.getConpassword(),
                user.getEmail());
        callReigster.enqueue(new Callback<RegestrationResponse>() {
            @Override
            public void onResponse(Call<RegestrationResponse> call, Response<RegestrationResponse> response) {
                Log.d("DDDDDD", response.body().toString());
                Toast.makeText(RegistrationActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                progressLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RegestrationResponse> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressLoading.setVisibility(View.GONE);
            }
        });
    }

}