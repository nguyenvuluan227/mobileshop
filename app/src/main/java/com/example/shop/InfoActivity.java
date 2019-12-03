package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.example.shop.model.Customer;
import com.example.shop.model.Order;
import com.example.shop.viewmodel.MainViewModel;

import java.util.List;

public class InfoActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtCustomerName, edtPhone, edtEmail;
    Button btnConfirm, btnCancel;
    MainViewModel viewModel;
    Integer idOrder, idProduct, number;
    long price;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        init();
        setupActionBar();
        setEventClick();

    }

    private void init() {
        edtCustomerName = findViewById(R.id.edtCustomername);
        edtPhone = findViewById(R.id.edtCustomerPhoneNumber);
        edtEmail = findViewById(R.id.edtCustomerEmail);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);
        toolbar = findViewById(R.id.toolBarInfoActivity);
    }

    private void setEventClick() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = edtCustomerName.getText().toString();
                final String phone = edtPhone.getText().toString();
                final String email = edtEmail.getText().toString();
                if (name.length() > 0 && phone.length() > 0 && email.length() > 0) {
                    viewModel = new MainViewModel();
                    viewModel.onValidCustomer(null, name, phone, email).observe(InfoActivity.this, new Observer<Customer>() {
                        @Override
                        public void onChanged(Customer customer) {
                            Log.d("ALO", customer.getId() + "");
                            idOrder = customer.getId();
                            setupData();
                        }
                    });
                } else {
                    Toast.makeText(InfoActivity.this, "Please Check Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    private void setupData() {
        for (int i = 0; i < MainActivity.arrayCart.size(); i++) {
            name = MainActivity.arrayCart.get(i).getProductName();
            idProduct = MainActivity.arrayCart.get(i).getId();
            price = MainActivity.arrayCart.get(i).getProductPrice();
            number = MainActivity.arrayCart.get(i).getProductNumber();
            viewModel.onValidOrder(null, idOrder, idProduct, name, price, number).observe(InfoActivity.this, new Observer<String>() {
                @Override
                public void onChanged(String orders) {
                    if (orders.equals("Success")) {
                        MainActivity.arrayCart.clear();
                        Toast.makeText(InfoActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
    }

}
