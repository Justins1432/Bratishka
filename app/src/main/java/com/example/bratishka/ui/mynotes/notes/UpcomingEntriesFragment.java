package com.example.bratishka.ui.mynotes.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bratishka.R;

public class UpcomingEntriesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upcoming_entries, container, false);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}