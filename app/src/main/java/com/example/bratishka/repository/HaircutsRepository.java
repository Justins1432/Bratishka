package com.example.bratishka.repository;

import android.content.Context;

import com.example.bratishka.model.Discount;
import com.example.bratishka.model.Haircut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HaircutsRepository {
    private Context context;
    private ArrayList<Haircut> haircuts;

    public HaircutsRepository(Context context) throws IOException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("haircuts.csv")))){
            String row = reader.readLine();
            this.haircuts = new ArrayList<>();
            while ((row = reader.readLine()) != null){
                String[] data = row.split(";");
                this.haircuts.add(new Haircut(data[0], data[1],
                        context.getResources().getIdentifier(data[2], "drawable", context.getPackageName())));
            }
        }
    }

    public ArrayList<Haircut> getHaircuts() {
        return haircuts;
    }

    @Override
    public String toString() {
        return "HaircutsRepository{" +
                "discounts=" + haircuts +
                '}';
    }
}
