package com.example.bratishka.ui.branches.branchinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bratishka.R;
import com.example.bratishka.adapter.BarbersAdapter;
import com.example.bratishka.adapter.HaircutsAdapter;
import com.example.bratishka.adapter.MessagesAdapter;
import com.example.bratishka.adapter.ShopAdapter;
import com.example.bratishka.model.Barber;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.model.Message;
import com.example.bratishka.model.Product;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.ui.branches.uibranches.MapBranchesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BranchInfoActivity extends AppCompatActivity {
    public static final String BRANCH_INFO = "branch_info";

    private TextView street;
    private GoogleMap map;
    private RecyclerView haircuts, messages, barbers;
    private List<Product> productList;
    private List<Haircut> haircutList;
    private List<Barber> barberList;
    private List<Message> messageList;
    private TextView shopInBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_info);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.street = findViewById(R.id.branchStreet);
        this.barbers = findViewById(R.id.recycler_view_barbers);
        this.messages = findViewById(R.id.recycler_view_messages);
        this.haircuts = findViewById(R.id.recycler_view_haircut_s);
        this.shopInBranch = findViewById(R.id.shopInBranch);

        String streetBranch = getIntent().getStringExtra("street");
        this.street.setText(streetBranch);

        initMap();
        initBarbers();
        initShop();
        initMessages();
        initHaircuts();
    }

    private void initBarbers() {
        int id = getIntent().getIntExtra("idBranch", -1);

        NetworkService.getInstance().getBratishkaApi()
                .getBarbersBranch(id)
                .enqueue(new Callback<List<Barber>>() {
                    @Override
                    public void onResponse(Call<List<Barber>> call, Response<List<Barber>> response) {
                        barberList = response.body();
                        LinearLayoutManager manager
                                = new LinearLayoutManager(BranchInfoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        barbers.setLayoutManager(manager);
                        barbers.setAdapter(new BarbersAdapter(BranchInfoActivity.this, barberList));
                    }

                    @Override
                    public void onFailure(Call<List<Barber>> call, Throwable t) {
                        Toast.makeText(BranchInfoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.fillInStackTrace();
                    }
                });

    }

    private void initMap() {
        SupportMapFragment mapFragment
                = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapBranch);

        mapFragment.getMapAsync(googleMap -> {
            map = googleMap;

            double latitude = getIntent().getDoubleExtra("latitude", -1);
            double longitude = getIntent().getDoubleExtra("longitude", -1);

            LatLng lng = new LatLng(latitude, longitude);
            MarkerOptions options = new MarkerOptions().position(lng)
                    .icon(BitmapDescriptorFactory.fromBitmap(getScaledBitmap(R.drawable.marker)));
            map.addMarker(options);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));

        });
    }

    private void initShop() {
        this.shopInBranch.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = LayoutInflater.from(this);
            View inflate = inflater.inflate(R.layout.shop_in_branch_show, null);
            RecyclerView recyclerView = inflate.findViewById(R.id.recycler_view_shop);
            NetworkService.getInstance().getBratishkaApi()
                    .getProducts()
                    .enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                            productList = response.body();
                            LinearLayoutManager manager
                                    = new LinearLayoutManager(BranchInfoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(new ShopAdapter(BranchInfoActivity.this, productList));
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {
                            Toast.makeText(BranchInfoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            t.fillInStackTrace();
                        }
                    });

            alertDialog.setView(inflate);
            alertDialog.show();
        });
    }

    private void initMessages() {
        NetworkService.getInstance().getBratishkaApi()
                .getMessages()
                .enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                        messageList = response.body();
                        LinearLayoutManager manager
                                = new LinearLayoutManager(BranchInfoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        messages.setLayoutManager(manager);
                        messages.setAdapter(new MessagesAdapter(BranchInfoActivity.this, messageList));
                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {
                        Toast.makeText(BranchInfoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.fillInStackTrace();
                    }
                });
    }

    private void initHaircuts() {
        NetworkService.getInstance().getBratishkaApi()
                .getHaircuts()
                .enqueue(new Callback<List<Haircut>>() {
                    @Override
                    public void onResponse(Call<List<Haircut>> call, Response<List<Haircut>> response) {
                        haircutList = response.body();
                        LinearLayoutManager manager
                                = new LinearLayoutManager(BranchInfoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        haircuts.setLayoutManager(manager);
                        haircuts.setAdapter(new HaircutsAdapter(BranchInfoActivity.this, haircutList));
                    }

                    @Override
                    public void onFailure(Call<List<Haircut>> call, Throwable t) {
                        Toast.makeText(BranchInfoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        t.fillInStackTrace();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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