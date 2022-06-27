package com.example.bratishka.ui.entry.bookahaircut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.SchedulesAdapter;
import com.example.bratishka.adapter.SpinnerBranchAdapter;
import com.example.bratishka.model.Branch;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.model.Resp;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveQueueActivity extends AppCompatActivity {
    public static final String NAME_HAIRCUT_LIVE = "name_haircut_live";
    private TextView hName, hPrice, txtDate, txtTime;
    private Spinner spinner;
    private Button btnRecord;
    private List<Branch> branches;
    private Haircut haircut;
    private int id;
    private String selectedDay;
    private SchedulesAdapter adapter;
    private String date;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_queue);
        initComponents();
        initPoints();
        initRecordLiveQueue();
    }

    private void initComponents() {
        this.haircut = (Haircut) this.getIntent().getSerializableExtra(NAME_HAIRCUT_LIVE);
        this.hPrice = findViewById(R.id.ptPrice);
        this.hName = findViewById(R.id.ptDescription);
        this.spinner = findViewById(R.id.snrPoints);
        this.btnRecord = findViewById(R.id.rcButton);
        this.txtDate = findViewById(R.id.dateLiveQueue);
        this.txtTime = findViewById(R.id.timeLiveQueue);
        this.hPrice.setText(this.haircut.getPrice());
        this.hName.setText(this.haircut.getName());
    }

    private void initPoints() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(Constants.PREFERENCES_USER, Context.MODE_PRIVATE);
        String idCity = sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);

        NetworkService.getInstance().getBratishkaApi().getCityBranches(idCity)
                .enqueue(new Callback<List<Branch>>() {
                    @Override
                    public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
                        branches = response.body();
                        SpinnerBranchAdapter adapter
                                = new SpinnerBranchAdapter(LiveQueueActivity.this, R.layout.custom_branch_adapter, branches);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Branch branch = (Branch) adapterView.getSelectedItem();
                                String branchId = branch.getId();
                                id = Integer.parseInt(branchId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Branch>> call, Throwable t) {
                        Toast.makeText(LiveQueueActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.fillInStackTrace();
                    }
                });
    }

    private void initRecordLiveQueue() {
        this.txtDate.setOnClickListener(viewDate -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.create();
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.calendar_view, null);
            final CalendarView calendarView = view.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener((calendarView1, year, month, day) -> {
                int uYear = year;
                int uMonth = month;
                int uDay = day;
                selectedDay = LocalDate.of(uYear, uMonth + 1, uDay).getDayOfWeek().toString().toLowerCase();
                date = String.format("%02d", uDay) + "-" + String.format("%02d", (uMonth + 1)) + "-" + uYear;
                this.txtDate.setText(date);
                dialog.cancel();
            });
            calendarView.setMinDate(System.currentTimeMillis() - 1000);
            dialog.setView(view);
            dialog.show();
        });

        this.txtTime.setOnClickListener(viewTime -> {
            if (selectedDay == null){
                Toast.makeText(this, "Не выбрана дата", Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.create();
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.schedule_alert_dialog, null);
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view_schedules);
            Button btnSelect = view.findViewById(R.id.btnSelect);
            LinearLayoutManager manager
                    = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            ArrayList<String> times = new ArrayList<>();
            times.add("10:00");
            times.add("12:00");
            times.add("14:00");
            times.add("16:00");
            times.add("18:00");
            times.add("20:00");
            adapter = new SchedulesAdapter(this, times);
            recyclerView.setAdapter(adapter);

            btnSelect.setOnClickListener(viewBtn ->{
                selectedItem = adapter.getSelectedItem();
                txtTime.setText(selectedItem);
                dialog.cancel();
            });

            dialog.setView(view);
            dialog.show();
        });

        this.btnRecord.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
            String email = preferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

            try {
                String name = this.haircut.getName();
                String price = this.haircut.getPrice();

                if (date == null || selectedItem == null){
                    Toast.makeText(this, "Данные для записи не выбраны!", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date _date = dateFormat.parse(date);
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                    String format = dateFormat1.format(_date);
                    NetworkService.getInstance().getBratishkaApi().addLiveQueueRecord(format, email, id, name, price, selectedItem)
                            .enqueue(new Callback<Resp>() {
                                @Override
                                public void onResponse(Call<Resp> call, Response<Resp> response) {
                                    Resp resp = response.body();
                                    if (resp.getStatus().equals("success")){
                                        Toast.makeText(LiveQueueActivity.this, "Запись успешно создана!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(LiveQueueActivity.this, resp.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Resp> call, Throwable t) {
                                    Toast.makeText(LiveQueueActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                    t.fillInStackTrace();
                                }
                            });
                }

            }catch (ParseException e){
                e.printStackTrace();
            }
        });

    }

}