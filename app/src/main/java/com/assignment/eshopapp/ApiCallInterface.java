package com.assignment.eshopapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCallInterface {

    @GET("json")
    Call<Product> getProduct();

}
