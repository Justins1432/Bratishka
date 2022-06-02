package com.example.bratishka.selectcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.CityAdapter;
import com.example.bratishka.main.MainMenuActivity;
import com.example.bratishka.model.City;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.util.Constants;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCityActivity extends AppCompatActivity {
    private EditText searchCity;
    private RecyclerView recyclerViewCities;
    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city2);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_select_city);

        this.searchCity = findViewById(R.id.search_cities);

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
        preferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

        this.searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearchRecyclerView();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.recyclerViewCities = findViewById(R.id.recycler_view_select_city);

        SharedPreferences sharedPreferencesCity
                = getSharedPreferences(Constants.PREFERENCES_CITY, MODE_PRIVATE);
        String cityString = sharedPreferencesCity.getString(Constants.PREFERENCES_CITY_ID, null);

        if (cityString != null){
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }

        finish();

        initRecyclerViewCities();
    }

    private void initRecyclerViewCities() {

        NetworkService.getInstance()
                .getBratishkaApi()
                .getCities()
                .enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        cities = response.body();
                        recyclerViewCities.setLayoutManager(new LinearLayoutManager(SelectCityActivity.this));
                        recyclerViewCities.setAdapter(new CityAdapter(SelectCityActivity.this, cities));
                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {
                        Toast.makeText(SelectCityActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

    }

    private void initSearchRecyclerView() {
        String search = this.searchCity.getText().toString();
        NetworkService.getInstance()
                .getBratishkaApi()
                .getSearchCities(search)
                .enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        cities = response.body();
                        recyclerViewCities.setLayoutManager(new LinearLayoutManager(SelectCityActivity.this));
                        recyclerViewCities.setAdapter(new CityAdapter(SelectCityActivity.this, cities));

                        if (cities.isEmpty()){
                            initRecyclerViewCities();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {
                        Toast.makeText(SelectCityActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mainMenu) {
            Intent intent = new Intent(SelectCityActivity.this, MainMenuActivity.class);
            startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}