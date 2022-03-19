package com.example.bratishka.selectcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.bratishka.R;
import com.example.bratishka.main.MainMenuActivity;

public class SelectCityActivity extends AppCompatActivity {
    private EditText searchCity;
    private RecyclerView recyclerViewCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city2);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_select_city);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.searchCity = findViewById(R.id.search_cities);
        this.recyclerViewCities = findViewById(R.id.recycler_view_select_city);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.mainMenu:
                Intent intent = new Intent(SelectCityActivity.this, MainMenuActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}