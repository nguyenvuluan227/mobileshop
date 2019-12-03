package com.example.shop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.shop.model.ProductType;
import com.example.shop.retrofit.RequestAPI;
import com.example.shop.retrofit.RetrofitInit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductTypeRepository {

    private static ProductTypeRepository repository = null;
    private RequestAPI requestAPI;

    private ProductTypeRepository() {
        requestAPI = RetrofitInit.ApiInit();
    }

    public synchronized static ProductTypeRepository getInstance() {
        if (repository == null) {
            repository = new ProductTypeRepository();
        }
        return repository;
    }

    public MutableLiveData<List<ProductType>> getProductType() {
        final MutableLiveData<List<ProductType>> productTypeResponse = new MutableLiveData<>();
        requestAPI.getProductType().enqueue(new Callback<List<ProductType>>() {
            @Override
            public void onResponse(Call<List<ProductType>> call, Response<List<ProductType>> response) {
                productTypeResponse.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<ProductType>> call, Throwable t) {
                t.printStackTrace();
                Log.d("ALO","Fail" + t.getMessage());
            }
        });
        return productTypeResponse;
    }
}
