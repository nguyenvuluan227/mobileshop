package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.shop.adapter.phone.PhoneActivityAdapter;
import com.example.shop.model.Product;
import com.example.shop.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhoneActivity extends AppCompatActivity {

    MainViewModel viewModel;
    ArrayList<Product> arrayPhone;
    String idtype;
    LinearLayoutManager layoutManager;
    PhoneActivityAdapter adapter;
    RecyclerView recyclerViewPhoneActivity;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        init();
        getData();
        setupViewModel();
        setupActionbar();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarPhoneActivity);
        recyclerViewPhoneActivity = findViewById(R.id.recyclerViewPhoneActivity);
        layoutManager = new LinearLayoutManager(PhoneActivity.this, RecyclerView.VERTICAL, false);
    }

    private void getData() {
        Intent phoneIntent = getIntent();
        idtype = phoneIntent.getStringExtra("idtype");

    }

    private void setupViewModel() {
        viewModel = new MainViewModel();
        viewModel.onValidProduct(idtype).observe(PhoneActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                arrayPhone = new ArrayList<>();
                arrayPhone.addAll(products);
                adapter = new PhoneActivityAdapter(arrayPhone);
                recyclerViewPhoneActivity.setLayoutManager(layoutManager);
                recyclerViewPhoneActivity.setAdapter(adapter);
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
                Intent intent = new Intent(PhoneActivity.this,ProductDetailActivity.class);
                intent.putExtra("productDetail",arrayPhone.get(position));
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
                Intent intent = new Intent(PhoneActivity.this,CartActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
