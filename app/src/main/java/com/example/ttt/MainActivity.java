package com.example.ttt;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin =findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  email=etUsername.getText().toString();
                String  password=etPassword.getText().toString();
                if(email.isEmpty()|| password.isEmpty()){
                    Toast.makeText(MainActivity.this, "fill all the fields", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(password.length()<6){
                    Toast.makeText(MainActivity.this, "password must be more than 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivity.this, "invalid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(MainActivity.this, "failed the login", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  email=etUsername.getText().toString();
                String  password=etPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "signup success ", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(MainActivity.this, "signup failed ", Toast.LENGTH_SHORT).show();
                        }



                    }
                });

            }
        });


    }
}