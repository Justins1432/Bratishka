package com.example.bratishka.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.bratishka.R;
import com.example.bratishka.main.registration.RegistrationActivity;
import com.example.bratishka.selectcity.SelectCityActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textViewRegistration;
    private TextView textViewForgotPassword;
    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        this.textViewRegistration = findViewById(R.id.registrationUser);
        this.textViewForgotPassword = findViewById(R.id.forgotPassUser);
        this.buttonSignIn = findViewById(R.id.btnSignIn);

        this.textViewRegistration.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        this.textViewForgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        this.buttonSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
            startActivity(intent);
        });

    }

}