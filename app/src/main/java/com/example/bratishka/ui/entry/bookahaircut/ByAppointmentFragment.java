package com.example.bratishka.ui.entry.bookahaircut;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bratishka.R;


public class ByAppointmentFragment extends Fragment {
    private RecyclerView recyclerViewBarbers;
    private TextView chooseDate, chooseTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_by_appointment, container, false);

        this.recyclerViewBarbers = view.findViewById(R.id.barbers);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2,
                LinearLayoutManager.VERTICAL, false);

        /*BarberRepository repository = new BarberRepository(view.getContext());
        ArrayList<Barber> barbers = repository.getBarbers();
        this.recyclerViewBarbers.setLayoutManager(layoutManager);
        this.recyclerViewBarbers.setAdapter(new BarbersAdapter(view.getContext(), barbers));

        this.chooseDate = view.findViewById(R.id.chooseDate);
        this.chooseTime = view.findViewById(R.id.chooseTime);

        this.chooseDate.setOnClickListener(viewChooseDate -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            AlertDialog alertDialog = builder.create();
            LayoutInflater inflaterCalendar = LayoutInflater.from(view.getContext());
            View viewCalendar = inflaterCalendar.inflate(R.layout.calendar_view, null);
            final CalendarView calendarView = viewCalendar.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener((calendarView1, year, month, day) ->{
                int mYear = year;
                int mMonth = month;
                int mDay = day;
                String date = mDay + "." + (mMonth + 1) + "." + mYear;
                this.chooseDate.setText(date);
                alertDialog.cancel();
            });
            calendarView.setMinDate(System.currentTimeMillis() - 1000);
            alertDialog.setView(viewCalendar);
           // builder.setView(viewCalendar);
            alertDialog.show();
        });*/

        /*this.chooseTime.setOnClickListener(viewChooseTime ->{
            //Выбор времени
        });*/

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}