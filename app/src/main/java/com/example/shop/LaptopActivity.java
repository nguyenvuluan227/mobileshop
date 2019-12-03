package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.adapter.OnItemClickListener;
import com.example.shop.adapter.laptop.LaptopActivityAdapter;
import com.example.shop.model.Product;
import com.example.shop.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class LaptopActivity extends AppCompatActivity {

    MainViewModel viewModel;
    ArrayList<Product> arrayLaptop;
    String idtype;
    LinearLayoutManager layoutManager;
    LaptopActivityAdapter adapter;
    RecyclerView recyclerViewLaptopActivity;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        init();
        getData();
        setupViewModel();
        setupActionbar();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarLaptopActivity);
        recyclerViewLaptopActivity = findViewById(R.id.recyclerViewLaptopActivity);
        layoutManager = new LinearLayoutManager(LaptopActivity.this, RecyclerView.VERTICAL, false);
    }

    private void getData() {
        Intent phoneIntent = getIntent();
        idtype = phoneIntent.getStringExtra("idtype");

    }

    private void setupViewModel() {
        viewModel = new MainViewModel();
        viewModel.onValidProduct(idtype).observe(LaptopActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                arrayLaptop = new ArrayList<>();
                arrayLaptop.addAll(products);
                adapter = new LaptopActivityAdapter(arrayLaptop);
                recyclerViewLaptopActivity.setLayoutManager(layoutManager);
                recyclerViewLaptopActivity.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                setupEventClick();
            }
        });
    }

    private void setupActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupEventClick() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(LaptopActivity.this, ProductDetailActivity.class);
                intent.putExtra("productDetail", arrayLaptop.get(position));
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
                Intent intent = new Intent(LaptopActivity.this,CartActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}