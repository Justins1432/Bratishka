package com.example.bratishka.main;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bratishka.R;
import com.example.bratishka.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bratishka.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {

    private ActivityMainMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.entry_id, R.id.branches_id, R.id.profile_id, R.id.my_notes_id)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }

}