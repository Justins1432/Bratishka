package com.example.bratishka.adapter;

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
import com.example.bratishka.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private Context context;
    private List<Product> products;

    public ShopAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grid_products_list_item, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Product product = this.products.get(position);
        Glide.with(context).asBitmap().load(product.getIcon()).into(holder.imageViewProduct);
        holder.textViewPriceProduct.setText(product.getPrice());
        holder.textViewTypeProduct.setText(product.getType());
        holder.textViewDescriptionProduct.setText(product.getDescription());
        String rating = String.valueOf(product.getRating());
        holder.textViewRatingProduct.setText(rating);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct, imageViewAddProduct;
        TextView textViewPriceProduct, textViewTypeProduct;
        TextView textViewDescriptionProduct, textViewRatingProduct;
        ConstraintLayout layout;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.showProduct);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewPriceProduct = itemView.findViewById(R.id.priceProduct);
            textViewTypeProduct = itemView.findViewById(R.id.typeProduct);
            textViewDescriptionProduct = itemView.findViewById(R.id.descriptionProduct);
            textViewRatingProduct = itemView.findViewById(R.id.ratingProduct);
            imageViewAddProduct = itemView.findViewById(R.id.addProductBasket);
        }
    }
}
