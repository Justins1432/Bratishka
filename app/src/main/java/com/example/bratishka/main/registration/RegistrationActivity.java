package com.example.bratishka.main.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private EditText edtTxtInEmail;
    private Button btnGetCode;

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

        this.edtTxtInEmail = findViewById(R.id.inputNumberReg);
        this.btnGetCode = findViewById(R.id.btnGetCode);

        getButtonCode();
    }

    private void getButtonCode() {
        this.btnGetCode.setOnClickListener(view -> {
            String email = this.edtTxtInEmail.getText().toString();
            NetworkService.getInstance()
                    .getBratishkaApi()
                    .getCode(email)
                    .enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();
                            if (email.isEmpty()){
                                Toast.makeText(RegistrationActivity.this, "Поле пустое!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(RegistrationActivity.this, RegistrationActivityOne.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }
                            Toast.makeText(RegistrationActivity.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            Toast.makeText(RegistrationActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });
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