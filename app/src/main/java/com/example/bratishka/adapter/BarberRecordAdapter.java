package com.example.bratishka.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bratishka.R;
import com.example.bratishka.model.Barber;
import com.example.bratishka.ui.entry.bookahaircut.ByAppointmentFragment;

import java.util.List;

public class BarberRecordAdapter extends RecyclerView.Adapter<BarberRecordAdapter.BarberRecordHolder> {
    private List<Barber> barberList;
    private Context context;

    private int selected;
    private Barber selectedItem;

    public BarberRecordAdapter(Context context, List<Barber> barberList){
        this.context = context;
        this.barberList = barberList;
        this.selectedItem = this.barberList.get(0);
    }


    @NonNull
    @Override
    public BarberRecordAdapter.BarberRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.barbers_item, parent, false);
        return new BarberRecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarberRecordAdapter.BarberRecordHolder holder, int position) {
        Barber barber = this.barberList.get(position);
        Glide.with(context).asBitmap().load(barber.getImage()).into(holder.imgBarber);
        holder.nameBarber.setText(barber.getName());
        holder.positionBarber.setText(barber.getPosition());
        holder.view.setBackgroundColor(selected == position ? Color.GREEN : Color.TRANSPARENT);

    }

    @Override
    public int getItemCount() {
        return this.barberList.size();
    }

    public int getSelected() {
        return selected;
    }

    public Barber getSelectedItem() {
        return selectedItem;
    }

    public class BarberRecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgBarber;
        TextView nameBarber, positionBarber;
        View view;

        public BarberRecordHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;;
            view.setOnClickListener(this);
            imgBarber = itemView.findViewById(R.id.barberPhoto);
            nameBarber = itemView.findViewById(R.id.nameSurnameBarber);
            positionBarber = itemView.findViewById(R.id.positionBarber);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION){
                return;
            }

            notifyItemChanged(selected);
            selected = getAdapterPosition();
            selectedItem = barberList.get(selected);
            notifyItemChanged(selected);
        }
    }

}
