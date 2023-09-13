package com.capstone.capstoneProject.register.api;

import com.capstone.capstoneProject.register.model.LoginRequest;
import com.capstone.capstoneProject.register.model.RegisterRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterApi {

    @GET("/auth/roles/list")
    Call<List<String>> getUserRoles();

    @GET("/master/order/status/list")
    Call<List<String>> getOrderStatus();

    @POST("/auth/verify/credentials")
    Call<Boolean> validateCredentials(@Body LoginRequest request);

    @POST("/auth/register")
    Call<Boolean> registerUser(@Body RegisterRequest request);
}
