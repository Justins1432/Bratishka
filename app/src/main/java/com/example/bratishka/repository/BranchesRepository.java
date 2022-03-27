package com.example.bratishka.repository;

import android.content.Context;
import com.example.bratishka.model.Branch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BranchesRepository {
    private Context context;
    private ArrayList<Branch> branches;

    public BranchesRepository(Context context) throws IOException {
        this.context = context;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.getAssets().open("buildings.csv")))) {
            String row = reader.readLine();
            this.branches = new ArrayList<>();
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(";");
                branches.add(new Branch(data[0], Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        context.getResources().getIdentifier(data[3], "drawable", context.getPackageName())));
            }
        }
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    @Override
    public String toString() {
        return "BranchesRepository{" +
                "branches=" + branches +
                '}';
    }
}
