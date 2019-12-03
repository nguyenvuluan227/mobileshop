package com.example.shop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.shop.retrofit.RequestAPI;
import com.example.shop.retrofit.RetrofitInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {

    private static OrderRepository repository = null;
    private RequestAPI requestAPI;

    private OrderRepository() {
        requestAPI = RetrofitInit.ApiInit();
    }

    public synchronized static OrderRepository getInstance() {
        if (repository == null) {
            repository = new OrderRepository();
        }
        return repository;
    }

    public MutableLiveData<String> getOrder(Integer id,
                                            Integer idorder,
                                            Integer idproduct,
                                            String name,
                                            long price,
                                            Integer number) {
        final MutableLiveData<String> orderResponse = new MutableLiveData<>();
        requestAPI.postOrder(id, idorder, idproduct, name, price, number).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                orderResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Log.d("ALO", "Fail" + t.getMessage());
            }
        });
        return orderResponse;
    }
}
