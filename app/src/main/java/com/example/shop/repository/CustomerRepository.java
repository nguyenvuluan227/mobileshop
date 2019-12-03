package com.example.shop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.shop.model.Customer;
import com.example.shop.retrofit.RequestAPI;
import com.example.shop.retrofit.RetrofitInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    private static CustomerRepository repository = null;
    private RequestAPI requestAPI;

    private CustomerRepository() {
        requestAPI = RetrofitInit.ApiInit();
    }

    public synchronized static CustomerRepository getInstance() {
        if (repository == null) {
            repository = new CustomerRepository();
        }
        return repository;
    }

    public MutableLiveData<Customer> getCustomer(Integer id, String name, String phone, String email) {
        final MutableLiveData<Customer> customerResponse = new MutableLiveData<>();
        requestAPI.customerInfo(id, name, phone, email).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                customerResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
                Log.d("ALO", "Fail" + t.getMessage());
            }
        });
        return customerResponse;
    }
}
