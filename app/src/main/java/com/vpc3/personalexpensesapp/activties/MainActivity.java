package com.vpc3.personalexpensesapp.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.vpc3.personalexpensesapp.R;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText uname, upassword;
    Button login, register;
    SharedPreferences preferences;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
       // int x = 5/0;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("KEY_UN", uname.getText().toString());
//                editor.putString("KEY_PASS", upassword.getText().toString());
//                editor.apply();
//                Intent i = new Intent(MainActivity.this, ExpensesActivity.class);
//                i.putExtra("KEY_UN", uname.getText().toString());
//                startActivity(i);
                loginWithFirebase(uname.getText().toString(), upassword.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
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
        db = FirebaseFirestore.getInstance();
        getPrefData();
    }


    private void getPrefData() {
        uname.setText(preferences.getString("KEY_UN", null));
        upassword.setText(preferences.getString("KEY_PASS", null));
    }

    private void loginWithFirebase(String username, String password) {
        db.collection("users")
                .whereEqualTo("UserName", username)
                .whereEqualTo("Password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG_LOGIN_DATA", document.getId() + " => " + document.getData());
                                Map<String, Object> map = document.getData();
                                if (map.size() > 0) {
                                    Intent i = new Intent(MainActivity.this, ExpensesActivity.class);
                                    i.putExtra("KEY_UN", uname.getText().toString());
                                    startActivity(i);
                                    return;
                                }else{
                                    Toast.makeText(MainActivity.this, "Invalid Login Data", Toast.LENGTH_SHORT).show();
                                }
                            }
                            Toast.makeText(MainActivity.this, "Invalid Login Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}