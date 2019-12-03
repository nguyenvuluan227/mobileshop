package com.example.shop.retrofit;

import com.example.shop.model.Customer;
import com.example.shop.model.Order;
import com.example.shop.model.Product;
import com.example.shop.model.ProductType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestAPI {

    @GET("getproducttype.php")
    Call<List<ProductType>> getProductType();

    @GET("getallproduct.php")
    Call<List<Product>> getAllProduct();

    @FormUrlEncoded
    @POST("getproduct.php")
    Call<List<Product>> getProduct(@Field("idtype") String idtype);

    @FormUrlEncoded
    @POST("customerinfo.php")
    Call<Customer> customerInfo(@Field("id") Integer id,
                                @Field("customername") String name,
                                @Field("phonenumber") String phone,
                                @Field("email") String emai);

    @FormUrlEncoded
    @POST("productorderdetail.php")
    Call<String> postOrder(@Field("id") Integer id,
                                @Field("idorder") Integer idorder,
                                @Field("idproduct") Integer idproduct,
                                @Field("productname") String name,
                                @Field("productprice") long price,
                                @Field("productnumber") Integer number);

}
