package com.example.bratishka.ui.entry.uientry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.HaircutsAdapter;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.selectcity.SelectCityActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HaircutsFragments extends Fragment {
    private RecyclerView recyclerViewHaircuts;
    private View root;
    private List<Haircut> haircuts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_haircuts_fragments, container, false);

        this.recyclerViewHaircuts = root.findViewById(R.id.recycler_view_haircuts);

        /*try {
            HaircutsRepository repository = new HaircutsRepository(view.getContext());
            ArrayList<Haircut> haircuts = repository.getHaircuts();
            this.recyclerViewHaircuts.setLayoutManager(new LinearLayoutManager(view.getContext()));
            this.recyclerViewHaircuts.setAdapter(new HaircutsAdapter(view.getContext(), haircuts));
        }catch (IOException e){
            e.printStackTrace();
        }*/

        NetworkService.getInstance()
                .getBratishkaApi()
                .getHaircuts()
                .enqueue(new Callback<List<Haircut>>() {
                    @Override
                    public void onResponse(Call<List<Haircut>> call, Response<List<Haircut>> response) {
                        haircuts = response.body();
                        recyclerViewHaircuts.setLayoutManager(new LinearLayoutManager(root.getContext()));
                        recyclerViewHaircuts.setAdapter(new HaircutsAdapter(root.getContext(), haircuts));
                    }

                    @Override
                    public void onFailure(Call<List<Haircut>> call, Throwable t) {
                        Toast.makeText(root.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}