package com.example.bratishka.ui.entry.addtobasket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.bratishka.R;
import com.example.bratishka.model.Product;

public class InfoProductActivity extends AppCompatActivity {
    public static final String PRODUCT = "product";
    private TextView price, description;
    private Product product;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_product);
        initComponents();
    }

    private void initComponents() {
        this.product = (Product) this.getIntent().getSerializableExtra(PRODUCT);
        initTextView();
        initBtnAddBasket();
    }

    private void initTextView() {
        this.price = findViewById(R.id.txtInfoPrice);
        this.description = findViewById(R.id.txtInfoDescription);
        this.price.setText(product.getPrice());
        this.description.setText(product.getDescription());
    }

    private void initBtnAddBasket(){
        this.button = findViewById(R.id.btnAddBasket);
        this.button.setOnClickListener(view -> {

        });
    }

}