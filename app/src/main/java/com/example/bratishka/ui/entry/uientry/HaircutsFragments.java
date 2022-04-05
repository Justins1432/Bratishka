package com.example.bratishka.ui.entry.uientry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bratishka.R;
import com.example.bratishka.adapter.HaircutsAdapter;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.repository.HaircutsRepository;

import java.io.IOException;
import java.util.ArrayList;

public class HaircutsFragments extends Fragment {
    private RecyclerView recyclerViewHaircuts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_haircuts_fragments, container, false);

        this.recyclerViewHaircuts = view.findViewById(R.id.recycler_view_haircuts);

        try {
            HaircutsRepository repository = new HaircutsRepository(view.getContext());
            ArrayList<Haircut> haircuts = repository.getHaircuts();
            this.recyclerViewHaircuts.setLayoutManager(new LinearLayoutManager(view.getContext()));
            this.recyclerViewHaircuts.setAdapter(new HaircutsAdapter(view.getContext(), haircuts));
        }catch (IOException e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}