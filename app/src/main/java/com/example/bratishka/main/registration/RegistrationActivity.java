package com.example.bratishka.main.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.bratishka.R;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextInputNumber;
    private Button buttonGetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_registration);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.editTextInputNumber = findViewById(R.id.inputNumberReg);
        this.buttonGetCode = findViewById(R.id.btnGetCode);

        getButtonCode();
    }

    private void getButtonCode() {
        this.buttonGetCode.setOnClickListener(view -> {
            //Ввод номера телефона
            //Отправка запроса на сервер
            //Переход на другое окно регистрации

            Intent intent = new Intent(RegistrationActivity.this, RegistrationActivityOne.class);
            finish();
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}