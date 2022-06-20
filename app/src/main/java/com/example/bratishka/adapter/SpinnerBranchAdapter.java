package com.example.bratishka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bratishka.R;
import com.example.bratishka.model.Branch;
import com.example.bratishka.ui.entry.bookahaircut.BookAHaircutActivity;
import com.example.bratishka.ui.entry.bookahaircut.ByAppointmentFragment;

import java.util.List;

public class SpinnerBranchAdapter extends ArrayAdapter<Branch> {
    private LayoutInflater inflater;

    public SpinnerBranchAdapter(@NonNull Context context, int resource, @NonNull List<Branch> branchList) {
        super(context, resource, branchList);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.custom_branch_adapter, null, true);

        Branch branch = getItem(position);
        String id = branch.getId();

        TextView txtStreet = view.findViewById(R.id.street_branch_adapter);
        txtStreet.setText(branch.getStreet());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_branch_adapter, parent, false);

        Branch branch = getItem(position);

        TextView txtStreet = convertView.findViewById(R.id.street_branch_adapter);
        txtStreet.setText(branch.getStreet());

        return convertView;
    }
}
