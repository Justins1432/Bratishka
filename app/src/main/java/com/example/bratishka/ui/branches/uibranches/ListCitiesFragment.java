package com.example.bratishka.ui.branches.uibranches;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.BranchesAdapter;
import com.example.bratishka.model.Branch;
import com.example.bratishka.repository.BranchesRepository;

import java.io.IOException;
import java.util.ArrayList;

public class ListCitiesFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Toast.makeText(getContext(), "List Fragment Create", Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);

        this.recyclerView = view.findViewById(R.id.addresses_recycler_view);
        this.editText = view.findViewById(R.id.searchAddress);

        try {
            BranchesRepository repository = new BranchesRepository(view.getContext());
            ArrayList<Branch> branches = repository.getBranches();
            this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            this.recyclerView.setAdapter(new BranchesAdapter(branches));
        }catch (IOException e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Toast.makeText(getContext(), "List fragment deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getContext(), "List fragment resume!", Toast.LENGTH_SHORT).show();
    }
}