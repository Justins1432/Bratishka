package com.example.bratishka.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bratishka.R;
import com.example.bratishka.model.Discount;

import java.util.ArrayList;
import java.util.Random;

public class DiscountsAdapter extends RecyclerView.Adapter<DiscountsAdapter.DiscountsViewHolder> {
    private ArrayList<Discount> discounts;

    /*private ArrayList<String> discount = new ArrayList<>();
    private ArrayList<String> textDiscount = new ArrayList<>();
    private ArrayList<String> imageDiscount = new ArrayList<>();*/

    private Context context;

    public DiscountsAdapter(Context context, ArrayList<Discount> discounts) {
        this.context = context;
        this.discounts = discounts;
    }

    /*public DiscountsAdapter(Context context, ArrayList<String> discount,
                            ArrayList<String> textDiscount, ArrayList<String> imageDiscount){
        this.context = context;
        this.discount = discount;
        this.textDiscount = textDiscount;
        this.imageDiscount = imageDiscount;
    }*/

    @NonNull
    @Override
    public DiscountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_discounts_item, parent, false);
        return new DiscountsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountsViewHolder holder, int position) {
        Discount discount = this.discounts.get(position);

        Random random = new Random();
        /*holder.imageView.setBackgroundColor(Color.argb(200,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)));*/

        /*holder.discount.setText(discount.get(position));
        holder.nameDiscount.setText(textDiscount.get(position));*/

        holder.discount.setText(discount.getType());
        holder.nameDiscount.setText(discount.getText());

        Glide.with(context).asBitmap().load(discount.getIdResource()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public class DiscountsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView discount, nameDiscount;

        public DiscountsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewDiscount);
            this.discount = itemView.findViewById(R.id.discount);
            this.nameDiscount = itemView.findViewById(R.id.nameDiscount);
        }

    }

}
