package com.example.bratishka.ui.entry.uientry.products;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.ShopAdapter;
import com.example.bratishka.model.Product;
import com.example.bratishka.repository.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PowderForHairOfStoreFragment extends Fragment {
    private View root;

    private RecyclerView recyclerViewPowderProduct;
    private List<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_powder_for_hair_of_store, container, false);

        recyclerViewPowderProduct = root.findViewById(R.id.recycler_view_powder_products);

        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 2,
                LinearLayoutManager.VERTICAL, false);

        NetworkService.getInstance()
                .getBratishkaApi()
                .getTypeProducts(3)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        products = response.body();
                        recyclerViewPowderProduct.setLayoutManager(layoutManager);
                        recyclerViewPowderProduct.setAdapter(new ShopAdapter(root.getContext(), products));
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
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