package com.example.bratishka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bratishka.R;
import com.example.bratishka.model.Branch;
import com.example.bratishka.ui.branches.branchinfo.BranchInfoActivity;
import com.example.bratishka.ui.entry.bookahaircut.BookAHaircutActivity;
import com.example.bratishka.ui.entry.bookahaircut.ByAppointmentFragment;

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

        holder.layout.setOnClickListener(view -> {
            String id = branch.getId();
            String streetBranch = branch.getStreet();
            String latitude = branch.getLatitude();
            String longitude = branch.getLongitude();

            int idBranch = Integer.parseInt(id);
            double brLatitude = Double.parseDouble(latitude);
            double brLongitude = Double.parseDouble(longitude);

            Intent intent = new Intent(context, BranchInfoActivity.class);
            intent.putExtra(BranchInfoActivity.BRANCH_INFO, branch);
            intent.putExtra("idBranch", idBranch);
            intent.putExtra("street", streetBranch);
            intent.putExtra("latitude", brLatitude);
            intent.putExtra("longitude", brLongitude);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public class BranchesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout layout;

        public BranchesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtBranchesAddress);
            layout = itemView.findViewById(R.id.branchesForeground);
        }
    }
}
