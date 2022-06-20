package com.example.bratishka.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bratishka.R;
import com.example.bratishka.main.MainMenuActivity;
import com.example.bratishka.model.City;
import com.example.bratishka.ui.branches.uibranches.ListBranchesFragment;
import com.example.bratishka.util.Constants;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private Context context;
    private List<City> cities;

    public CityAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.city_items, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = this.cities.get(position);
        holder.textViewCity.setText(city.getName());
        holder.textViewCount.setText(city.getCount());

        String count = city.getCount();
        int brCount = Integer.parseInt(count);
        int temp = brCount % 10;

        if (temp == 1) {
            holder.textViewDecline.setText("парикмахерская");
        } else if (temp == 0 || (temp >= 2 && temp <= 9)) {
            holder.textViewDecline.setText("парикмахерских");
        }

        holder.layout.setOnClickListener(view -> {
            String cityId = city.getId();

            SharedPreferences sharedPreferences
                    = context.getSharedPreferences(Constants.PREFERENCES_USER, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constants.PREFERENCES_USER_CITY_ID, cityId);
            editor.apply();

            Intent intent = new Intent(context, MainMenuActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("city_id", cityId);
            ListBranchesFragment fragment = new ListBranchesFragment();
            fragment.setArguments(bundle);
            context.startActivity(intent);

            Toast.makeText(context, "Выбран город " + city.getName(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return this.cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCity, textViewCount, textViewDecline;
        ConstraintLayout layout;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.city_name);
            textViewCount = itemView.findViewById(R.id.countBranches);
            textViewDecline = itemView.findViewById(R.id.decline);
            layout = itemView.findViewById(R.id.cityID);
        }
    }
}