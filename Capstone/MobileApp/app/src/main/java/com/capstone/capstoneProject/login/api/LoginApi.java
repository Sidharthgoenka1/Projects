package com.capstone.capstoneProject.login.api;

import com.capstone.capstoneProject.register.model.LoginRequest;
import com.capstone.capstoneProject.register.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("/auth/login")
    Call<LoginResponse> loginApi(@Body LoginRequest request);
}
