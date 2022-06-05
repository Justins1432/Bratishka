package com.example.bratishka.ui.branches.uibranches;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.model.Branch;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.ui.branches.branchinfo.BranchInfoActivity;
import com.example.bratishka.util.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapBranchesFragment extends Fragment {
    private GoogleMap map;
    private List<Branch> branches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_branches, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_branches);

        mapFragment.getMapAsync(googleMap -> {
            SharedPreferences sharedPreferences
                    = view.getContext().getSharedPreferences(Constants.PREFERENCES_USER, Context.MODE_PRIVATE);
            String idCity = sharedPreferences.getString(Constants.PREFERENCES_USER_CITY_ID, null);

            map = googleMap;
            Context context = view.getContext();
            NetworkService.getInstance().getBratishkaApi()
                    .getCityBranches(idCity)
                    .enqueue(new Callback<List<Branch>>() {
                        @Override
                        public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
                            branches = response.body();

                            for (Branch branch : branches) {
                                String street = branch.getStreet();
                                String latitude = branch.getLatitude();
                                String longitude = branch.getLongitude();

                                double dLatitude = parseDouble(latitude);
                                double dLongitude = parseDouble(longitude);

                                LatLng latLng = new LatLng(dLatitude, dLongitude);

                                MarkerOptions options = new MarkerOptions().position(latLng)
                                        .icon(BitmapDescriptorFactory.fromBitmap(getScaledBitmap(R.drawable.marker)))
                                        .title(street);
                                map.addMarker(options);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Branch>> call, Throwable t) {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });
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