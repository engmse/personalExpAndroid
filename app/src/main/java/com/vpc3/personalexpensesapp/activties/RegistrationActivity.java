package com.vpc3.personalexpensesapp.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.databinding.ActivityRegistrationBinding;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ActivityRegistrationBinding bindingRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingRegister = ActivityRegistrationBinding.inflate(getLayoutInflater());
        View view = bindingRegister.getRoot();
        setContentView(view);
        View v = findViewById(R.id.bar);
        ImageView back = v.findViewById(R.id.backBtn);
        db = FirebaseFirestore.getInstance();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bindingRegister.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bindingRegister.userNameEt.getText().toString().isEmpty()){
                    bindingRegister.userNameEt.setError("");
                    return;
                }
                registerUserToFirebase(bindingRegister.userNameEt.getText().toString(),
                        bindingRegister.mobileNo.getText().toString(),
                        bindingRegister.userPassword.getText().toString());
            }
        });
    }

    private void registerUserToFirebase(String uname, String mobile, String password) {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("UserName", uname);
        user.put("Mobile", mobile);
        user.put("Password", password);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistrationActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrationActivity.this, "User Not created", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}