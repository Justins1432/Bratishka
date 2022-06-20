package com.example.bratishka.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.main.registration.RegistrationActivity;
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.selectcity.SelectCityActivity;
import com.example.bratishka.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txtViewRegistration, txtViewForgotPassword;
    private Button buttonSignIn;
    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initRegistration();
        initForgotPass();
        initAuth();
    }

    private void initComponents() {
        this.txtViewRegistration = findViewById(R.id.registrationUser);
        this.txtViewForgotPassword = findViewById(R.id.forgotPassUser);
        this.buttonSignIn = findViewById(R.id.btnSignIn);
        this.edtEmail = findViewById(R.id.authEmail);
        this.edtPassword = findViewById(R.id.authPassword);
    }

    private void initRegistration() {
        this.txtViewRegistration.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void initForgotPass() {
        this.txtViewForgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    private void initAuth() {
        this.buttonSignIn.setOnClickListener(view -> {
            String email = this.edtEmail.getText().toString();
            String password = this.edtPassword.getText().toString();

            NetworkService.getInstance()
                    .getBratishkaApi()
                    .getAuth(email, password)
                    .enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();

                            if (email.isEmpty() && password.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Поля пустые", Toast.LENGTH_SHORT).show();
                            } else if (email.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Поле Логин пустое!", Toast.LENGTH_SHORT).show();
                            } else if (password.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Поле Пароль пустое!", Toast.LENGTH_SHORT).show();
                            }

                            if (resp.getStatus().equals("OK")) {
                                Toast.makeText(MainActivity.this, resp.getStatus(), Toast.LENGTH_SHORT).show();

                                SharedPreferences preferences =
                                        getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putString(Constants.PREFERENCES_USER_EMAIL, email);
                                editor.apply();

                                Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                Toast.makeText(MainActivity.this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

}