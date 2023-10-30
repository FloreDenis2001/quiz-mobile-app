package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginIntro extends AppCompatActivity {

    private Button btnGetStart;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            Toast.makeText(this,"User is already logged in !",Toast.LENGTH_SHORT).show();
            redirectMain();
        }

        btnGetStart = findViewById(R.id.flore_denis_buttonGetStart);
        btnGetStart.setOnClickListener(getStarted());
    }

    public View.OnClickListener getStarted() {
        return view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        };
    }

    public void redirectMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}