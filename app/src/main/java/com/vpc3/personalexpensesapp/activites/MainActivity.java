package com.vpc3.personalexpensesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vpc3.personalexpensesapp.Common;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.model.User;
import com.vpc3.personalexpensesapp.roomdb.RoomDatabaseSingleton;

public class MainActivity extends AppCompatActivity {

    EditText uname, upassword;
    Button login, register;
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
                editor.putString("KEY_UN", uname.getText().toString());
                editor.putString("KEY_PASS", upassword.getText().toString());
                editor.apply();
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, com.vpc3.personalexpensesapp.activites.RegistrationActivity.class);
                startActivity(i);
            }
        });

    }


    private void initViews() {
        preferences = getSharedPreferences("PREF_SETTINGS", MODE_PRIVATE);
        uname = findViewById(R.id.userNameEt);
        upassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        getPrefData();
    }


    private void getPrefData() {
        uname.setText(preferences.getString("KEY_UN", null));
        upassword.setText(preferences.getString("KEY_PASS", null));
    }


    private void login() {
        String un = uname.getText().toString();
        String pass = upassword.getText().toString();
        final User[] u = new User[1];
        class LoginTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                u[0] = RoomDatabaseSingleton.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .getDao()
                        .login(un, pass);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (u[0] != null) {
                    Intent i = new Intent(MainActivity.this, ExpensesActivity.class);
                    i.putExtra("KEY_UN", uname.getText().toString());
                    Common.user = u[0];
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "User Name or password incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        new LoginTask().execute();
    }
}