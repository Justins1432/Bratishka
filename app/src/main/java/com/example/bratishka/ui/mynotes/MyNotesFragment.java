package com.example.bratishka.ui.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.RecordAdapter;
import com.example.bratishka.model.Record;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.util.Constants;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNotesFragment extends Fragment {
    private MyNotesViewModel mViewModel;
    private View root;
    private TextView txtDayOfWeek;
    private RecyclerView recyclerView;

    public static MyNotesFragment newInstance() {
        return new MyNotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_my_notes, container, false);
        initComponents();
        dayOfWeek();
        initRecords();
        return root;
    }

    private void initComponents() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.toolbar_title_mynotes_fragment);
        this.txtDayOfWeek = root.findViewById(R.id.txtDayOfWeek);
        this.recyclerView = root.findViewById(R.id.recycler_view_record_items);
    }

    private void dayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY:
                this.txtDayOfWeek.setText("Понедельник");
                break;
            case Calendar.TUESDAY:
                this.txtDayOfWeek.setText("Вторник");
                break;
            case Calendar.WEDNESDAY:
                this.txtDayOfWeek.setText("Среда");
                break;
            case Calendar.THURSDAY:
                this.txtDayOfWeek.setText("Четверг");
                break;
            case Calendar.FRIDAY:
                this.txtDayOfWeek.setText("Пятница");
                break;
            case Calendar.SATURDAY:
                this.txtDayOfWeek.setText("Суббота");
                break;
            case Calendar.SUNDAY:
                this.txtDayOfWeek.setText("Воскресенье");
        }
    }

    private void initRecords() {
        SharedPreferences preferences
                = root.getContext().getSharedPreferences(Constants.PREFERENCES_USER, Context.MODE_PRIVATE);
        String email = preferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

        NetworkService.getInstance().getBratishkaApi().getRecords(email)
                .enqueue(new Callback<List<Record>>() {
                    @Override
                    public void onResponse(Call<List<Record>> call, Response<List<Record>> response) {
                        List<Record> records = response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(new RecordAdapter(getContext(), records));
                    }

                    @Override
                    public void onFailure(Call<List<Record>> call, Throwable t) {
                        t.fillInStackTrace();
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyNotesViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecords();
    }
}