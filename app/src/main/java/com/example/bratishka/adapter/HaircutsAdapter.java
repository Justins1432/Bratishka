package com.example.bratishka.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bratishka.R;
import com.example.bratishka.model.Haircut;

import java.util.ArrayList;
import java.util.List;

public class HaircutsAdapter extends RecyclerView.Adapter<HaircutsAdapter.HaircutsViewHolder> {
    private List<Haircut> haircuts;
    private Context context;

    public HaircutsAdapter(Context context, List<Haircut> haircuts) {
        this.context = context;
        this.haircuts = haircuts;
    }

    @NonNull
    @Override
    public HaircutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.haircuts_list_item, parent, false);
        return new HaircutsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HaircutsViewHolder holder, int position) {
        Haircut haircut = haircuts.get(position);
        holder.textViewPrice.setText(haircut.getPrice());
        holder.textViewNameHaircuts.setText(haircut.getName());
        Glide.with(context).asBitmap().load(haircut.getIcon()).into(holder.imageView);
        /*holder.layout.setOnClickListener(view -> {

        });*/
    }

    @Override
    public int getItemCount() {
        return haircuts.size();
    }

    public class HaircutsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewPrice, textViewNameHaircuts;
        ConstraintLayout layout;

        public HaircutsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewHaircuts);
            textViewPrice = itemView.findViewById(R.id.priceHaircuts);
            textViewNameHaircuts = itemView.findViewById(R.id.nameHaircuts);
            layout = itemView.findViewById(R.id.haircut);
        }
    }
}
