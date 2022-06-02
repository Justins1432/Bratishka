package com.example.bratishka.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bratishka.R;
import com.example.bratishka.model.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchesViewHolder> {
    private List<Branch> branches;
    private Context context;
    public BranchesAdapter(Context context, List<Branch> branches) {
        this.context = context;
        this.branches = branches;
    }

    @NonNull
    @Override
    public BranchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.branches_item, parent, false);
        return new BranchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchesViewHolder holder, int position) {
        Branch branch = this.branches.get(position);
        holder.textView.setText(branch.getStreet());
    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public class BranchesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public BranchesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtBranchesAddress);
        }
    }
}
