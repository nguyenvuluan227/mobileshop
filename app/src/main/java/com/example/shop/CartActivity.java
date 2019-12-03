package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.adapter.product.CartAdapter;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    Button btnPayment, btnShopping;
    static TextView tvTotalPrice;
    Toolbar toolbar;
    CartAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        setupActionBar();
        eventUtil();
        seteventClick();
    }

    private void init() {
        btnPayment = findViewById(R.id.btnPayment);
        btnShopping = findViewById(R.id.btnContinueShopping);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        toolbar = findViewById(R.id.toolBarCartActivity);
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        adapter = new CartAdapter(MainActivity.arrayCart);
        recyclerViewCart.setLayoutManager(layoutManager);
        recyclerViewCart.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void eventUtil() {
        long total = 0;
        for (int i = 0; i < MainActivity.arrayCart.size(); i++) {
            total += MainActivity.arrayCart.get(i).getProductPrice();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvTotalPrice.setText("Total Price: " + decimalFormat.format(total) + "$");
        }
    }
    private void seteventClick() {
        btnShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrayCart.size() > 0) {
                    Intent intent = new Intent(CartActivity.this,InfoActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(CartActivity.this, "Please add Product to Your Cart before Paying", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
