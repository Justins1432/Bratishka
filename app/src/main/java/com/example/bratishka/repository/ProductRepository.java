package com.example.bratishka.repository;

import android.content.Context;

import com.example.bratishka.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductRepository {
    private Context context;
    private ArrayList<Product> products;

    public ProductRepository(Context context) throws IOException {
        this.context = context;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("store_items.csv")))){
            String row = reader.readLine();
            this.products = new ArrayList<>();
            while ((row = reader.readLine()) != null){
                String[] data = row.split(";");
                this.products.add(new Product(data[0], data[1], data[2], Double.parseDouble(data[3]),
                        context.getResources().getIdentifier(data[4], "drawable", context.getPackageName())));
            }
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ProductRepository{" +
                "products=" + products +
                '}';
    }
}
