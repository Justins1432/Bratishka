package com.example.bratishka.ui.profile.editprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.main.MainActivity;
import com.example.bratishka.model.Resp;
import com.example.bratishka.model.User;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.util.Constants;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private EditText edtSurname, edtName, edtFathername;
    private EditText edtCity, edtNumber;
    private ImageView imgProfile, downloadImgProfile;
    private TextView txtDateBirth;
    private Button btnChangePass, btnSave;
    private Context context;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_edit_profile);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.edtSurname = findViewById(R.id.edtSurname);
        this.edtName = findViewById(R.id.edtName);
        this.edtFathername = findViewById(R.id.edtPatronymic);
        this.txtDateBirth = findViewById(R.id.txtDateBirth);
        this.edtCity = findViewById(R.id.edtCityUser);
        this.edtNumber = findViewById(R.id.edtNumberPhone);
        this.btnChangePass = findViewById(R.id.btn_change_pass);
        this.btnSave = findViewById(R.id.btnSave);
        this.imgProfile = findViewById(R.id.photo_profile_user);
        this.downloadImgProfile = findViewById(R.id.download_photo);

        changePasswordUser();
        editDataUser();
    }

    private void changePasswordUser() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
        String email = sharedPreferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

        btnChangePass.setOnClickListener(viewChangeButton -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = LayoutInflater.from(this);
            View inflate = inflater.inflate(R.layout.recovery_pass_show, null);
            final EditText edtInNumber = inflate.findViewById(R.id.inNumber);
            final TextView txtGetCode = inflate.findViewById(R.id.gtCode);
            final TextView txtEmUser = inflate.findViewById(R.id.txtEmailUser);
            final TextInputEditText nwPass = inflate.findViewById(R.id.nwPassword);
            final TextInputEditText repeatPass = inflate.findViewById(R.id.rptPassword);
            final Button btnApply = inflate.findViewById(R.id.apBtn);

            txtEmUser.setText(email);

            txtGetCode.setOnClickListener(viewGetCode -> {
                NetworkService.getInstance().getBratishkaApi().getCode(email)
                        .enqueue(new Callback<Resp>() {
                            @Override
                            public void onResponse(Call<Resp> call, Response<Resp> response) {
                                Resp resp = response.body();
                                if (resp.getStatus().equals("OK")) {
                                    Toast.makeText(EditProfileActivity.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
                                } else {
                                    Toast.makeText(EditProfileActivity.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Resp> call, Throwable t) {
                                Toast.makeText(EditProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                t.fillInStackTrace();
                            }
                        });
            });

            btnApply.setOnClickListener(viewApplyButton -> {
                String code = edtInNumber.getText().toString();
                String newPassword = nwPass.getText().toString();
                String repeatPassword = repeatPass.getText().toString();

                NetworkService.getInstance().getBratishkaApi().getRecovery(email, code, newPassword)
                        .enqueue(new Callback<Resp>() {
                            @Override
                            public void onResponse(Call<Resp> call, Response<Resp> response) {
                                Resp resp = response.body();

                                if (code.isEmpty()) {
                                    Toast.makeText(EditProfileActivity.this, "Поле ввода кода пустое!", Toast.LENGTH_SHORT).show();
                                } else if (!newPassword.equals(repeatPassword)) {
                                    Toast.makeText(EditProfileActivity.this, "Пароли не соответсвуют, повторите ввод", Toast.LENGTH_SHORT).show();
                                } else if (resp.getStatus().equals("error")) {
                                    Toast.makeText(EditProfileActivity.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    sharedPreferences.getString(Constants.PREFERENCES_USER_EMAIL, null);
                                    sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.apply();
                                    finish();
                                    Toast.makeText(EditProfileActivity.this, resp.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Resp> call, Throwable t) {
                                Toast.makeText(EditProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                t.fillInStackTrace();
                            }
                        });
                //alertDialog.cancel();
            });

            alertDialog.setView(inflate);
            alertDialog.show();
        });
    }

    private void editDataUser() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
        String email = sharedPreferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

        String surname = this.edtSurname.getText().toString();
        String name = this.edtName.getText().toString();
        String fathername = this.edtFathername.getText().toString();
        String number = this.edtNumber.getText().toString();

        String dateBorn = this.txtDateBirth.getText().toString();
        this.txtDateBirth.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(EditProfileActivity.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, dateSetListener,
                    year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = day + "-" + month + "-" + year;
            txtDateBirth.setText(date);
        };

        String city = this.edtCity.getText().toString();

        this.btnSave.setOnClickListener(view -> {
            NetworkService.getInstance().getBratishkaApi().editProfile(email,
                    surname, name, fathername, dateBorn, number, city)
                    .enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();
                            if (resp.getStatus().equals("success")){
                                Toast.makeText(EditProfileActivity.this, resp.toString(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditProfileActivity.this, "Не отредактировано!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            Toast.makeText(EditProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            t.fillInStackTrace();
                        }
                    });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit_app_id) {
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
            sharedPreferences.getString(Constants.PREFERENCES_USER_EMAIL, null);
            sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            finish();
            Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}