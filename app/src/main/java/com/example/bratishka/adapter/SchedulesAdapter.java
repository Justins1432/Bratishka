package com.example.bratishka.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bratishka.R;
import com.example.bratishka.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.SchedulesViewHolder> {
    private ArrayList<String> strings;
    private Context context;

    private int selectedPosition;
    private String selectedItem;

    public SchedulesAdapter(Context context, ArrayList<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public SchedulesAdapter.SchedulesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.schedule_item, parent, false);
        return new SchedulesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchedulesAdapter.SchedulesViewHolder holder, int position) {
        String s = this.strings.get(position);
        //holder.txtTimeSchedule.setText(schedule.);
        holder.view.setBackgroundColor(selectedPosition == position ? Color.GREEN : Color.TRANSPARENT);
        holder.txtTimeSchedule.setText(s);

    }

    @Override
    public int getItemCount() {
        return this.strings.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public class SchedulesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTimeSchedule;
        View view;

        public SchedulesViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
            txtTimeSchedule = itemView.findViewById(R.id.timeSchedule);
        }

        @Override
        public void onClick(View view) {
            if(getAdapterPosition() == RecyclerView.NO_POSITION){
                return;
            }
            notifyItemChanged(selectedPosition);
            selectedPosition = getAdapterPosition();
            selectedItem = strings.get(selectedPosition);
            notifyItemChanged(selectedPosition);
        }
    }
}
