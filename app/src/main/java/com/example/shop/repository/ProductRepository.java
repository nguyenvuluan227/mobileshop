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

public class ProductRepository {

    private static ProductRepository repository = null;
    private RequestAPI requestAPI;

    private ProductRepository() {
        requestAPI = RetrofitInit.ApiInit();
    }

    public synchronized static ProductRepository getInstance() {
        if (repository == null) {
            repository = new ProductRepository();
        }
        return repository;
    }

    public MutableLiveData<List<Product>> getProduct(String idtype) {
        final MutableLiveData<List<Product>> productResponse = new MutableLiveData<>();
        requestAPI.getProduct(idtype).enqueue(new Callback<List<Product>>() {
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
