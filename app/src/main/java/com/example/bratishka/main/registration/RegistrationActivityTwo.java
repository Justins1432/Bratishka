package com.example.bratishka.main.registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bratishka.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivityTwo extends AppCompatActivity {
    private EditText edtNumberPhone;
    private TextInputEditText edtPassword, edtRepeatPassword;
    private CheckBox rules;
    private Button btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        initComponents();
    }

    private void initComponents(){
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_registration_1);

        this.edtNumberPhone = findViewById(R.id.id_registration_number);
        this.edtPassword = findViewById(R.id.id_password_user);
        this.edtRepeatPassword = findViewById(R.id.id_repeat_password);
        this.rules = findViewById(R.id.id_checkbox_rules);
        this.btnRegistration = findViewById(R.id.id_btn_registration);
    }

}