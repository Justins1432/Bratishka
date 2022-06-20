package com.example.bratishka.ui.entry.bookahaircut;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bratishka.R;
import com.example.bratishka.adapter.BarberRecordAdapter;
import com.example.bratishka.adapter.SchedulesAdapter;
import com.example.bratishka.model.Barber;
import com.example.bratishka.model.Schedule;
import com.example.bratishka.repository.NetworkService;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ByAppointmentFragment extends Fragment {
    private RecyclerView recyclerViewBarbers;
    private TextView chooseDate, chooseTime;
    private TextView txtView;
    private List<Barber> barbers;
    private List<String> schedules;
    private View view;
    private BarberRecordAdapter adapter;
    private SchedulesAdapter schedulesAdapter;

    private String selectedDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_by_appointment, container, false);
        this.recyclerViewBarbers = view.findViewById(R.id.barbers);
        this.chooseDate = view.findViewById(R.id.chooseDate);
        this.chooseTime = view.findViewById(R.id.chooseTime);
        initBarbers();
        initRecord();
        return view;
    }

    private void initBarbers() {
        Bundle bundle = getArguments();

        if (bundle != null) {
            int branchID = bundle.getInt("idBranch", -1);

            NetworkService.getInstance().getBratishkaApi().getBarbersBranch(branchID)
                    .enqueue(new Callback<List<Barber>>() {
                        @Override
                        public void onResponse(Call<List<Barber>> call, Response<List<Barber>> response) {
                            barbers = new ArrayList<>(response.body());
                            recyclerViewBarbers.setLayoutManager(new LinearLayoutManager(view.getContext(),
                                    LinearLayoutManager.HORIZONTAL, false));
                            Context context = view.getContext();
                            adapter = new BarberRecordAdapter(context, barbers);
                            recyclerViewBarbers.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Barber>> call, Throwable t) {
                            Toast.makeText(view.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                            t.fillInStackTrace();
                        }
                    });
        }
    }

    private void initRecord() {
        SharedPreferences preferences
                = getContext().getSharedPreferences("RecordBarber", Context.MODE_PRIVATE);
        this.chooseDate.setOnClickListener(viewChooseDate -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            AlertDialog alertDialog = builder.create();
            LayoutInflater inflaterCalendar = LayoutInflater.from(view.getContext());
            View viewCalendar = inflaterCalendar.inflate(R.layout.calendar_view, null);
            final CalendarView calendarView = viewCalendar.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener((calendarView1, year, month, day) -> {
                int mYear = year;
                int mMonth = month;
                int mDay = day;
                selectedDay = LocalDate.of(mYear, mMonth + 1, mDay).getDayOfWeek().toString().toLowerCase();
                String date = String.format("%02d", mDay) + "." + String.format("%02d", (mMonth + 1)) + "." + mYear;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("date", date);
                editor.apply();
                this.chooseDate.setText(date);
                alertDialog.cancel();
            });
            calendarView.setMinDate(System.currentTimeMillis() - 1000);
            alertDialog.setView(viewCalendar);
            // builder.setView(viewCalendar);
            alertDialog.show();
        });

        this.chooseTime.setOnClickListener(viewChooseTime -> {
            if (selectedDay == null) {
                Toast.makeText(getContext(), "Не выбрана дата", Toast.LENGTH_SHORT).show();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            AlertDialog dialog = builder.create();
            LayoutInflater inflaterSchedule = LayoutInflater.from(getContext());
            View view = inflaterSchedule.inflate(R.layout.schedule_alert_dialog, null);
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view_schedules);
            Button buttonSelect = view.findViewById(R.id.btnSelect);
            LinearLayoutManager manager
                    = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);

            String barderId = adapter.getSelectedItem().getId();
            int idBarber = Integer.parseInt(barderId);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("barberID", idBarber);
            editor.apply();

            NetworkService.getInstance().getBratishkaApi().getTimes(barderId, selectedDay)
                    .enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            List<Schedule> body = response.body();
                            ArrayList<String> strings = getTimes(body, selectedDay);
                            schedulesAdapter = new SchedulesAdapter(view.getContext(), strings);
                            recyclerView.setAdapter(schedulesAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                            Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                            t.fillInStackTrace();
                        }
                    });

            buttonSelect.setOnClickListener(viewBtn -> {
                String selectedItem = schedulesAdapter.getSelectedItem();
                chooseTime.setText(selectedItem);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("time", selectedItem);
                editor1.apply();
                dialog.cancel();
            });

            dialog.setView(view);
            dialog.show();

        });

    }

    private ArrayList<String> getTimes(List<Schedule> schedules, String selectedDay) {
        ArrayList<String> res = new ArrayList<>();
        try {
            Schedule schedule = schedules.get(0);
            Field declaredField1 = schedule.getClass().getDeclaredField(selectedDay + "_start");
            declaredField1.setAccessible(true);
            String start = (String) declaredField1.get(schedule);
            Field declaredField2 = schedule.getClass().getDeclaredField(selectedDay + "_end");
            declaredField2.setAccessible(true);
            String end = (String) declaredField2.get(schedule);

            LocalTime localTime = LocalTime.parse(start);
            while (localTime.isBefore(LocalTime.parse(end))) {
                res.add(localTime.toString());
                localTime = localTime.plusMinutes(60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}