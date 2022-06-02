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
import com.example.bratishka.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.DiscountsViewHolder> {
    private List<Message> messages;
    private Context context;

    public MessagesAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public DiscountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_discounts_item, parent, false);
        return new DiscountsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountsViewHolder holder, int position) {
        Message message = this.messages.get(position);
        holder.discount.setText(message.getType());
        holder.nameDiscount.setText(message.getName());
        Glide.with(context).asBitmap().load(message.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class DiscountsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView discount, nameDiscount;

        public DiscountsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewDiscount);
            this.discount = itemView.findViewById(R.id.type_message);
            this.nameDiscount = itemView.findViewById(R.id.name_message);
        }

    }

}
