package com.example.bratishka.ui.profile.editprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.bratishka.R;
import com.example.bratishka.main.ForgotPasswordActivity;
import com.example.bratishka.main.MainActivity;
import com.example.bratishka.util.Constants;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editSurname, editName, editPatronymic;
    private EditText editEmail, editDateBirth, editCity;
    private Button btnChangePass, btnSave;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initComponents();
    }

    private void initComponents(){
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_edit_profile);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.editSurname = findViewById(R.id.edtSurname);
        this.editName = findViewById(R.id.edtName);
        this.editPatronymic = findViewById(R.id.edtPatronymic);
        this.editEmail = findViewById(R.id.edtEmailUser);
        this.editDateBirth = findViewById(R.id.edtDateBirth);
        this.editCity = findViewById(R.id.edtCityUser);
        this.btnChangePass = findViewById(R.id.btn_change_pass);
        this.btnSave = findViewById(R.id.btnSave);

        changePasswordUser();
        editDataUser();
    }

    private void changePasswordUser(){
        this.btnChangePass.setOnClickListener(view -> {
            Intent intent = new Intent(EditProfileActivity.this, ForgotPasswordActivity.class);
            finish();
            startActivity(intent);
        });
    }

    private void editDataUser(){
        //Код редактирования данных пользователя
        //После редактирования сохранить изменения


        this.btnSave.setOnClickListener(view -> {

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
        if (item.getItemId() == R.id.exit_app_id){
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
            sharedPreferences.getString(Constants.PREFERENCES_USER_EMAIL, null);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            finish();
            Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}