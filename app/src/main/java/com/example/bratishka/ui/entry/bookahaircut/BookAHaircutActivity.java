package com.example.bratishka.ui.entry.bookahaircut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.SpinnerBranchAdapter;
import com.example.bratishka.adapter.viewpager.ViewPagerEntries;
import com.example.bratishka.model.Branch;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.util.Constants;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAHaircutActivity extends AppCompatActivity {
    public static final String NAME_HAIRCUT = "name_haircut";
    private TextView textViewPrice, textViewName;
    private Haircut haircut;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Spinner spinner;
    private Button btnRecord;
    private List<Branch> branches;
    private int idBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ahaircut);
        try {
            initComponents();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() throws ParseException {
        this.haircut = (Haircut) this.getIntent().getSerializableExtra(NAME_HAIRCUT);
        this.btnRecord = findViewById(R.id.payButton);
        initTextView();
        initTabLayoutAndViewPager();
        initSpinner();
    }

    private void initTextView() {
        this.textViewPrice = findViewById(R.id.productPrice);
        this.textViewName = findViewById(R.id.productDescription);
        this.textViewPrice.setText(this.haircut.getPrice());
        this.textViewName.setText(this.haircut.getName());

    }

    private void initTabLayoutAndViewPager() {
        this.tabLayout = findViewById(R.id.tabLayoutEntry);
        this.viewPager = findViewById(R.id.viewPagerEntry);
        this.tabLayout.setupWithViewPager(viewPager);


        ViewPagerEntries viewPagerEntries = new ViewPagerEntries(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        viewPagerEntries.addFragment(new ByAppointmentFragment(), "По записи");
        viewPagerEntries.addFragment(new LiveQueueFragment(), "Живая очередь");
        viewPager.setAdapter(viewPagerEntries);
    }

    private void initSpinner() {
        this.spinner = findViewById(R.id.spinnerPoints);

        SharedPreferences sharedPreferences
                = getSharedPreferences(Constants.PREFERENCES_USER, Context.MODE_PRIVATE);
        String idCity = sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);

        NetworkService.getInstance().getBratishkaApi().getCityBranches(idCity)
                .enqueue(new Callback<List<Branch>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Branch>> call, Response<List<Branch>> response) {
                        branches = response.body();

                        SpinnerBranchAdapter adapter
                                = new SpinnerBranchAdapter(BookAHaircutActivity.this, R.layout.custom_branch_adapter, branches);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Branch branch = (Branch) adapterView.getSelectedItem();
                                String id = branch.getId();
                                idBranch = Integer.parseInt(id);
                                ByAppointmentFragment fragment = new ByAppointmentFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                Bundle bundle = new Bundle();
                                bundle.putInt("idBranch", idBranch);
                                fragment.setArguments(bundle);
                                transaction.replace(R.id.flLayout, fragment).commit();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Branch>> call, Throwable t) {
                        Toast.makeText(BookAHaircutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.fillInStackTrace();
                    }
                });

        this.btnRecord.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
            String email = preferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

            SharedPreferences pRecord = getSharedPreferences("RecordBarber", MODE_PRIVATE);
            String date = pRecord.getString("date", null);
            try {
                int barberID = pRecord.getInt("barberID", -1);
                String time = pRecord.getString("time", null);

                String name = this.haircut.getName();
                String price = this.haircut.getPrice();

                if (date == null || barberID == -1 || time == null) {
                    Toast.makeText(this, "Данные для записи не выбраны!", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
                    Date _date = dateFormat1.parse(date);
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    String format = dateFormat2.format(_date);
                    NetworkService.getInstance().getBratishkaApi()
                            .addRecord(format, email, idBranch, name, price, time, barberID)
                            .enqueue(new Callback<Resp>() {
                                @Override
                                public void onResponse(Call<Resp> call, Response<Resp> response) {
                                    Resp resp = response.body();

                                    if (resp.getStatus().equals("success")) {
                                        SharedPreferences.Editor editor = pRecord.edit();
                                        editor.clear();
                                        editor.apply();
                                        Toast.makeText(BookAHaircutActivity.this, "Запись успешно создана!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(BookAHaircutActivity.this, resp.getStatus(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Resp> call, Throwable t) {
                                    Toast.makeText(BookAHaircutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                    t.fillInStackTrace();
                                }
                            });
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
    }

}