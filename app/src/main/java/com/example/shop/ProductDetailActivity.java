package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.shop.model.Cart;
import com.example.shop.model.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    String id, productName, productPrice, productImage, productDescription;
    ImageView imvProductDetail;
    TextView tvProductDetailName, tvProductDetailPrice, tvProductDetailDescription;
    Toolbar toolbar;
    Spinner spinner;
    Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        getData();
        setData();
        setupActionBar();
        setupSpinner();
        setupEventClick();
    }

    private void init() {
        imvProductDetail = findViewById(R.id.imvProductDetail);
        tvProductDetailName = findViewById(R.id.tvProductDetailName);
        tvProductDetailPrice = findViewById(R.id.tvProductDetailPrice);
        tvProductDetailDescription = findViewById(R.id.tvProductDetailDescription);
        toolbar = findViewById(R.id.toolbarProductDetailActivity);
        spinner = findViewById(R.id.spinner);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    private void getData() {
        Product product;
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("productDetail");
        id = product.getId();
        productName = product.getProductname();
        productPrice = product.getProductprice();
        productImage = product.getProductimage();
        productDescription = product.getProductdescription();
    }

    private void setData() {
        Picasso.get().load(productImage).into(imvProductDetail);
        tvProductDetailName.setText(productName);
        tvProductDetailPrice.setText(productPrice + "$");
        tvProductDetailDescription.setText(productDescription);
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

    private void setupSpinner() {
        Integer[] number = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, number);
        spinner.setAdapter(arrayAdapter);
    }

    private void setupEventClick() {
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrayCart.size() > 0) {
                    int numb = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for (int i = 0; i < MainActivity.arrayCart.size(); i++) {
                        if (MainActivity.arrayCart.get(i).getId() == Integer.parseInt(id)) {
                            MainActivity.arrayCart.get(i).setProductNumber(MainActivity.arrayCart.get(i).getProductNumber() + numb);
                            if (MainActivity.arrayCart.get(i).getProductNumber() >= 10) {
                                MainActivity.arrayCart.get(i).setProductNumber(10);
                            }
                            MainActivity.arrayCart.get(i).setProductPrice(Integer.parseInt(productPrice) * MainActivity.arrayCart.get(i).getProductNumber());
                            exist = true;
                        }
                    }
                    if (exist == false) {
                        int number = Integer.parseInt(spinner.getSelectedItem().toString());
                        long newPrice = number * Integer.parseInt(productPrice);
                        MainActivity.arrayCart.add(new Cart(Integer.parseInt(id), productName, newPrice, productImage, number));
                    }

                } else {
                    int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    long newPrice = number * Integer.parseInt(productPrice);
                    MainActivity.arrayCart.add(new Cart(Integer.parseInt(id), productName, newPrice, productImage, number));
                }
                Intent intent = new Intent(ProductDetailActivity.this,CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCart:
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
