package com.example.bratishka.ui.branches.uibranches;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bratishka.R;
import com.example.bratishka.model.Branch;
import com.example.bratishka.repository.BranchesRepository;
import com.example.bratishka.ui.branches.branchinfo.BranchInfoActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MapBranchesFragment extends Fragment {
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_map_branches, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_branches);

        mapFragment.getMapAsync(googleMap -> {
            map = googleMap;
            try {
                Context context = view.getContext();
                BranchesRepository repository = new BranchesRepository(context);
                ArrayList<Branch> branches = repository.getBranches();

                for (Branch branch : branches){
                    String address = branch.getAddress();
                    double latitude = branch.getLatitude();
                    double longitude = branch.getLongitude();
                    int idResource = branch.getIdResource();

                    LatLng lng = new LatLng(latitude, longitude);
                    MarkerOptions options = new MarkerOptions().position(lng)
                            .icon(BitmapDescriptorFactory.fromBitmap(getScaledBitmap(idResource)));

                    map.addMarker(options);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));

                    map.setOnMarkerClickListener(marker -> {
                        Intent intent = new Intent(getContext(), BranchInfoActivity.class);
                        startActivity(intent);
                        return false;
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        return view;
    }

    public Bitmap getScaledBitmap(int resId) {
        int height = 150;
        int width = 150;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(resId);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        return smallMarker;
    }


}