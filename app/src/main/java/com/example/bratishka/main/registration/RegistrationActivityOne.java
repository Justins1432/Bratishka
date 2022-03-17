package com.example.bratishka.main.registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bratishka.R;

public class RegistrationActivityOne extends AppCompatActivity {
    private EditText number1, number2, number3, number4;
    private TextView textViewCheckNumber;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_one);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_registration_1);

        this.number1 = findViewById(R.id.numberOne);
        this.number2 = findViewById(R.id.numberTwo);
        this.number3 = findViewById(R.id.numberThree);
        this.number4 = findViewById(R.id.numberFour);
        this.textViewCheckNumber = findViewById(R.id.checkNumberPhone);

        this.textView = findViewById(R.id.goToRegTwo);
        this.textView.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivityOne.this, RegistrationActivityTwo.class);
            startActivity(intent);
        });

        checkNumberPhone();
    }

    private void checkNumberPhone(){
        this.textViewCheckNumber.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivityOne.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

}