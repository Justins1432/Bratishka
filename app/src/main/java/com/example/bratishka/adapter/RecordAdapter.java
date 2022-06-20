package com.example.bratishka.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bratishka.R;
import com.example.bratishka.model.Record;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private Context context;
    private List<Record> records;

    public RecordAdapter(Context context, List<Record> records) {
        this.context = context;
        this.records = records;
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.record_item, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordViewHolder holder, int position) {
        Record record = this.records.get(position);
        holder.txtName.setText(record.getName());
        holder.txtPrice.setText(record.getPrice());
        holder.txtBarberName.setText(record.getB_name());
    }

    @Override
    public int getItemCount() {
        return this.records.size();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtBarberName;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtH_name);
            txtPrice = itemView.findViewById(R.id.txtH_price);
            txtBarberName = itemView.findViewById(R.id.txtB_name);
        }
    }
}
