package com.example.shop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.shop.model.Product;
import com.example.shop.retrofit.RequestAPI;
import com.example.shop.retrofit.RetrofitInit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductRepository {

    private static AllProductRepository repository = null;
    private RequestAPI requestAPI;

    private AllProductRepository() {
        requestAPI = RetrofitInit.ApiInit();
    }

    public synchronized static AllProductRepository getInstance() {
        if (repository == null) {
            repository = new AllProductRepository();
        }
        return repository;
    }

    public MutableLiveData<List<Product>> getAllProduct() {
        final MutableLiveData<List<Product>> productResponse = new MutableLiveData<>();
        requestAPI.getAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Log.d("ALO", "Fail" + t.getMessage());
            }
        });
        return productResponse;
    }

}
