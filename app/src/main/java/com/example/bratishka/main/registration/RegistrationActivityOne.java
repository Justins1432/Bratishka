package com.example.bratishka.main.registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivityOne extends AppCompatActivity {
    private EditText number;
    private TextView txtViewReturn;
    private Button btnReviewCode;
    private TextView txtEmail;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_one);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_registration_1);

        this.number = findViewById(R.id.code_Reg);
        this.txtViewReturn = findViewById(R.id.checkEmail);
        this.btnReviewCode = findViewById(R.id.inCode);
        this.txtEmail = findViewById(R.id.txtEmail_Regis);

        this.txtViewReturn.setOnClickListener(view -> {
            finish();
        });

        checkEmail();
    }

    private void checkEmail(){
        Bundle arguments = getIntent().getExtras();
        String email = arguments.getString("email");
        this.txtEmail.setText(email);

        this.btnReviewCode.setOnClickListener(view -> {
            String code = this.number.getText().toString();
            NetworkService.getInstance()
                    .getBratishkaApi()
                    .getReview(email, code)
                    .enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();

                            if (resp.getStatus().equals("OK")){
                                Intent intent = new Intent(RegistrationActivityOne.this, RegistrationActivityTwo.class);
                                intent.putExtra("email", email);
                                intent.putExtra("code", code);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegistrationActivityOne.this, "Неверный код!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            Toast.makeText(RegistrationActivityOne.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

}