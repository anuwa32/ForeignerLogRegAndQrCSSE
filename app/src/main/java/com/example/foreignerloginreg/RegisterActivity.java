package com.example.foreignerloginreg;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText ForeignerEmail;
    TextInputEditText ForeignerPassword;
    TextView ForeignerLogin;
    Button btnReg;
    FirebaseAuth fBAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ForeignerEmail = findViewById(R.id.ForeignerEmail);
        ForeignerPassword = findViewById(R.id.ForeignerPass);
        ForeignerLogin = findViewById(R.id.ForeignerLogin);
        btnReg = findViewById(R.id.btnReg);

        fBAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(view ->{
            createUser();
        });

        ForeignerLogin.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }


    private void createUser(){
        String email = ForeignerEmail.getText().toString();
        String password = ForeignerPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            ForeignerEmail.setError("Email cannot be empty field");
            ForeignerEmail.requestFocus();

        }else if (TextUtils.isEmpty(password)){
            ForeignerPassword.setError("Password cannot be empty field");
            ForeignerPassword.requestFocus();

        }else{
            fBAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "You are registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}