package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private Button buttonSignUp;
    private FirebaseAuth firebaseAuth;

    private TextView btnLoginContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignUp = findViewById(R.id.flore_denis_signUpButton);

        btnLoginContainer = findViewById(R.id.flore_denis_txtAlreadyHave);

        btnLoginContainer.setOnClickListener(login());

        buttonSignUp.setOnClickListener(signUpUser());
    }

    public View.OnClickListener login() {
        return view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        };
    }

    private View.OnClickListener signUpUser() {
        return view -> {
            EditText emailEditText = findViewById(R.id.flore_denis_emailSignUp);
            EditText passwordEditText = findViewById(R.id.flore_denis_passwordSignUp);
            EditText confirmEditText = findViewById(R.id.flore_denis_confirmpasswordSignUp);

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmedPassword = confirmEditText.getText().toString();

            if (!password.equals(confirmedPassword)) {
                Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            } else if (email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
                Toast.makeText(this, "Email/Password cannot be empty!", Toast.LENGTH_LONG).show();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(SignupActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        };

    }
}
