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

public class SignupActivity extends AppCompatActivity {
    private Button buttonSignUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignUp = findViewById(R.id.flore_denis_signUpButton);

        buttonSignUp.setOnClickListener(v -> signUpUser());
    }


    private View.OnClickListener signUpUser() {

        EditText emailEditText = findViewById(R.id.flore_denis_emailSignUp);
        EditText passwordEditText = findViewById(R.id.flore_denis_passwordSignUp);
        EditText confirmEditText = findViewById(R.id.flore_denis_confirmpasswordSignUp);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmedPassword = confirmEditText.getText().toString();

        if (!password.equals(confirmedPassword)) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


        return null;
    }
}
