package com.example.bratishka.ui.entry.bookahaircut;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.bratishka.R;

public class LiveQueueFragment extends Fragment {
    private TextView chooseDateLiveQueue, chooseTimeLiveQueue;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_live_queue, container, false);

        this.chooseDateLiveQueue = view.findViewById(R.id.chooseDateLiveQueue);
        this.chooseTimeLiveQueue = view.findViewById(R.id.chooseTimeLiveQueue);

        this.chooseDateLiveQueue.setOnClickListener(view1 -> {
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
                this.chooseDateLiveQueue.setText(date);
                alertDialog.cancel();
            });
            calendarView.setMinDate(System.currentTimeMillis() - 1000);
            alertDialog.setView(viewCalendar);
            alertDialog.show();
        });

        this.chooseTimeLiveQueue.setOnClickListener(view2 ->{

        });

        return view;
    }
}