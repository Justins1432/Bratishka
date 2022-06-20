package com.example.bratishka.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.bratishka.R;
import com.example.bratishka.main.MainActivity;
import com.example.bratishka.main.MainMenuActivity;
import com.example.bratishka.selectcity.SelectCityActivity;
import com.example.bratishka.util.Constants;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this,
                    MainActivity.class);
            SplashScreenActivity.this.startActivity(intent);

            SharedPreferences sharedPreferences
                    = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
            String preferencesUserEmail = sharedPreferences.getString(Constants.PREFERENCES_USER_EMAIL, null);
            String preferencesUserCityID = sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);

            if (preferencesUserEmail != null && preferencesUserCityID == null){
                intent = new Intent(SplashScreenActivity.this, SelectCityActivity.class);
            }

            if (preferencesUserEmail != null && preferencesUserCityID != null){
                intent = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
            }

            if (preferencesUserEmail == null && preferencesUserCityID == null){
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            }

            startActivity(intent);
            SplashScreenActivity.this.finish();

        }, SPLASH_DISPLAY_LENGTH);

    }
}