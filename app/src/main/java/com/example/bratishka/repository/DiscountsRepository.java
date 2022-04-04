package com.example.bratishka.repository;

import android.content.Context;

import com.example.bratishka.model.Discount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DiscountsRepository {
    private Context context;
    private ArrayList<Discount> discounts;

    public DiscountsRepository(Context context) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("discounts.csv")))){
            String row = reader.readLine();
            this.discounts = new ArrayList<>();
            while ((row = reader.readLine()) != null){
                String[] data = row.split(";");
                discounts.add(new Discount(data[0], data[1],
                        context.getResources().getIdentifier(data[2], "drawable", context.getPackageName())));
            }
        }
    }

    public ArrayList<Discount> getDiscounts(){
        return discounts;
    }

    @Override
    public String toString() {
        return "DiscountsRepository{" +
                "discounts=" + discounts +
                '}';
    }
}
