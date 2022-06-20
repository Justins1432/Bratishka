package com.example.bratishka.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText edtInputNumber;
    private TextInputEditText newInputPass;
    private TextInputEditText rptInputPass;
    private TextView txtGetCode;
    private Button btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_forgot_pass);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.edtInputNumber = findViewById(R.id.inputNumber);
        this.newInputPass = findViewById(R.id.newPassword);
        this.rptInputPass = findViewById(R.id.repeatPassword);
        this.txtGetCode = findViewById(R.id.getCode);
        this.btnApply = findViewById(R.id.applyBtn);

        getCode();
        buttonApply();
    }

    private void getCode() {
        this.txtGetCode.setOnClickListener(view -> {
            int textNull = R.string.text_null;
            Toast.makeText(this, textNull, Toast.LENGTH_SHORT).show();
        });
    }

    private void buttonApply() {
        this.btnApply.setOnClickListener(view -> {
            //Восстановление доступа
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}