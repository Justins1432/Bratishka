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
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText edtInputEmail, edtInputCode;
    private TextInputEditText newInputPass;
    private TextInputEditText rptInputPass;
    private TextView txtGetCode, txtForgotUser;
    private Button btnApply;
    private String email;

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

        this.edtInputEmail = findViewById(R.id.inputEmail);
        this.edtInputCode = findViewById(R.id.inputCode);
        this.newInputPass = findViewById(R.id.newPassword);
        this.rptInputPass = findViewById(R.id.repeatPassword);
        this.txtGetCode = findViewById(R.id.getCode);
        this.txtForgotUser = findViewById(R.id.emailForgotUser);
        this.btnApply = findViewById(R.id.applyBtn);

        getCode();
        buttonApply();
    }

    private void getCode() {
        this.txtGetCode.setOnClickListener(view -> {
            email = this.edtInputEmail.getText().toString();
            if (!email.equals("")) {
                NetworkService.getInstance().getBratishkaApi().getCode(email)
                        .enqueue(new Callback<Resp>() {
                            @Override
                            public void onResponse(Call<Resp> call, Response<Resp> response) {
                                Resp resp = response.body();
                                txtForgotUser.setText(email);
                                if (resp.getStatus().equals("success")) {
                                    Toast.makeText(ForgotPasswordActivity.this, "Код отправлен на " + email, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgotPasswordActivity.this, resp.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Resp> call, Throwable t) {
                                Toast.makeText(ForgotPasswordActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                t.fillInStackTrace();
                            }
                        });
            } else {
                int textNull = R.string.text_null;
                Toast.makeText(this, textNull, Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void buttonApply() {
        this.btnApply.setOnClickListener(view -> {
            String code = this.edtInputCode.getText().toString();
            String nPass = this.newInputPass.getText().toString();
            String rPass = this.rptInputPass.getText().toString();

            NetworkService.getInstance().getBratishkaApi().getRecovery(email, code, nPass)
                    .enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();

                            if (code.isEmpty()){
                                Toast.makeText(ForgotPasswordActivity.this, "Поле Код пустое!", Toast.LENGTH_SHORT).show();
                            } else if (!nPass.equals(rPass)){
                                Toast.makeText(ForgotPasswordActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, resp.toString(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            Toast.makeText(ForgotPasswordActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            t.fillInStackTrace();
                        }
                    });

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}