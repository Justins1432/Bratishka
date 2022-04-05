package com.example.bratishka.ui.entry.uientry.shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bratishka.R;
import com.example.bratishka.adapter.ShopAdapter;
import com.example.bratishka.model.Product;
import com.example.bratishka.repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;

public class EntireRangeOfStoreFragment extends Fragment {
    private RecyclerView recyclerViewEntireProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_entire_range_of_store, container, false);

        this.recyclerViewEntireProducts = view.findViewById(R.id.recycler_view_entireProducts);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2,
                LinearLayoutManager.VERTICAL, false);


        try {
            ProductRepository repository = new ProductRepository(view.getContext());
            ArrayList<Product> products = repository.getProducts();
            this.recyclerViewEntireProducts.setLayoutManager(layoutManager);
            this.recyclerViewEntireProducts.setAdapter(new ShopAdapter(view.getContext(), products));
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