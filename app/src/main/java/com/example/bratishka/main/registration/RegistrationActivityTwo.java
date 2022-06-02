package com.example.bratishka.main.registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.main.MainActivity;
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivityTwo extends AppCompatActivity {
    private EditText edtNumber, codeRegistration;
    private TextInputEditText edtPassword, edtRepeatPassword;
    private TextView txtEmail;
    private Button btnRegistration;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        initComponents();
    }

    private void initComponents(){
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_registration_1);

        this.edtNumber = findViewById(R.id.phone_number_user);
        this.edtPassword = findViewById(R.id.id_password_user);
        this.edtRepeatPassword = findViewById(R.id.id_repeat_password);
        this.btnRegistration = findViewById(R.id.btn_registration);
        this.txtEmail = findViewById(R.id.emailRegUser);
        this.codeRegistration = findViewById(R.id.codeRegUser);

        check = (CheckBox) findViewById(R.id.checkbox_rules);
        String text = "Я ознакомился с <a style='color:red;' href='https://www.mozilla.org/ru/about/governance/policies/participation/'>правилами</a>, " +
                "политикой <a href='https://e-kontur.ru/enquiry/1318/privacy'>конфиденциальности</a> и принимаю их условия ";
        check.setText(Html.fromHtml(text));
        check.setMovementMethod(LinkMovementMethod.getInstance());

        getRegistration();
    }

    private void getRegistration(){
        Bundle arguments = getIntent().getExtras();
        String email = arguments.getString("email");
        String code = arguments.getString("code");
        this.codeRegistration.setText(code);
        this.txtEmail.setText(email);

        this.btnRegistration.setOnClickListener(view -> {
            String password = this.edtPassword.getText().toString();
            String repeatPassword = this.edtRepeatPassword.getText().toString();
            String number = this.edtNumber.getText().toString();


            NetworkService.getInstance()
                    .getBratishkaApi()
                    .getRegistration(email, password, number)
                    .enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();

                            if (number.isEmpty()){
                                Toast.makeText(RegistrationActivityTwo.this, "Номер телефона пустой!", Toast.LENGTH_SHORT).show();
                            }

                            if (!password.equals(repeatPassword)){
                                Toast.makeText(RegistrationActivityTwo.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                            }

                            String txtCheck = "Вы не ознакомились с правилами и политикой конфиденциальности";

                            if (!check.isChecked()){
                                Toast.makeText(RegistrationActivityTwo.this, txtCheck, Toast.LENGTH_SHORT).show();
                            }

                            if (resp.getStatus().equals("success")){
                                Intent intent = new Intent(RegistrationActivityTwo.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegistrationActivityTwo.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistrationActivityTwo.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            Toast.makeText(RegistrationActivityTwo.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }

}