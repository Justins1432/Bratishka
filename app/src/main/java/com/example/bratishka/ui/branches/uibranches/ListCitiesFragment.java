package com.example.bratishka.ui.branches.uibranches;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.BranchesAdapter;
import com.example.bratishka.model.Branch;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCitiesFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText editText;
    private View view;
    private List<Branch> branches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Toast.makeText(getContext(), "List Fragment Create", Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_cities, container, false);

        this.recyclerView = view.findViewById(R.id.addresses_recycler_view);
        this.editText = view.findViewById(R.id.searchAddress);

        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        initRecyclerViewBranches();

        return view;
    }

    private void initRecyclerViewBranches() {
        SharedPreferences sharedPreferences
                = view.getContext().getSharedPreferences(Constants.PREFERENCES_USER, Context.MODE_PRIVATE);
        String idCity = sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);

        NetworkService.getInstance()
                .getBratishkaApi()
                .getCityBranches(idCity)
                .enqueue(new Callback<List<Branch>>() {
                    @Override
                    public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
                        branches = response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        recyclerView.setAdapter(new BranchesAdapter(view.getContext(), branches));
                    }

                    @Override
                    public void onFailure(Call<List<Branch>> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initSearch() {
        String street = this.editText.getText().toString();

        NetworkService.getInstance()
                .getBratishkaApi().getSearchBranches(street)
                .enqueue(new Callback<List<Branch>>() {
                    @Override
                    public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
                        branches = response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        recyclerView.setAdapter(new BranchesAdapter(view.getContext(), branches));

                        if (branches.isEmpty()) {
                            initRecyclerViewBranches();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Branch>> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}