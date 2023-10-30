package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button logIn;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logIn = findViewById(R.id.flore_denis_loginButton);
        logIn.setOnClickListener(v -> login());
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public View.OnClickListener login() {

        email = findViewById(R.id.flore_denis_emailInput);
        password = findViewById(R.id.flore_denis_passwordInput);


        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email / Password cannot be empty !", Toast.LENGTH_SHORT).show();
        } else {

            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Error login", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return null;
    }


}