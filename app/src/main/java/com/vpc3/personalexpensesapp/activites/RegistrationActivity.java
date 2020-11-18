package com.vpc3.personalexpensesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.model.User;
import com.vpc3.personalexpensesapp.roomdb.RoomDatabaseSingleton;

public class RegistrationActivity extends AppCompatActivity {

    EditText uname, mobile, password, cPassword;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();

    }

    private void initViews() {
        View v = findViewById(R.id.bar);
        uname = findViewById(R.id.userNameEt);
        mobile = findViewById(R.id.mobileNo);
        password = findViewById(R.id.userPassword);
        cPassword = findViewById(R.id.userCPassword);
        regBtn = findViewById(R.id.registerBtn);
        ImageView back = v.findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        String un = uname.getText().toString();
        String mob = mobile.getText().toString();
        String pass = password.getText().toString();
        String cpass = cPassword.getText().toString();

        if (un.trim().isEmpty()) {
            uname.setError("Required!");
            return;
        }


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                User u = new User();
                u.setMobile(mob);
                u.setPassowrd(pass);
                u.setUsername(un);
                RoomDatabaseSingleton.getInstance(getApplicationContext()).getAppDatabase().getDao().insertUser(u);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(RegistrationActivity.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        SaveTask s = new SaveTask();
        s.execute();


    }
}