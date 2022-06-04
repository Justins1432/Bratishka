package com.example.bratishka.adapter;

import android.content.Context;
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

import java.util.List;

public class BarbersAdapter extends RecyclerView.Adapter<BarbersAdapter.BarberViewHolder> {
    private Context context;
    private List<Barber> barbers;

    public BarbersAdapter(Context context, List<Barber> barbers){
        this.context = context;
        this.barbers = barbers;
    }

    @NonNull
    @Override
    public BarberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.barbers_item, parent, false);
        return new BarberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarberViewHolder holder, int position) {
        Barber barber = barbers.get(position);

        holder.txtName.setText(barber.getName());
        holder.txtPosition.setText(barber.getPosition());
        Glide.with(context).asBitmap().load(barber.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return barbers.size();
    }

    public class BarberViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtPosition;
        ImageView imageView;

        public BarberViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.nameSurnameBarber);
            txtPosition = itemView.findViewById(R.id.positionBarber);
            imageView = itemView.findViewById(R.id.barberPhoto);
        }
    }
}
