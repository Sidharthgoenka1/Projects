package com.capstone.capstoneProject.dashboard.api;


import com.capstone.capstoneProject.dashboard.model.InventoryMasterResponsePojo;
import com.capstone.capstoneProject.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DashboardApi {

    @POST("/master/user/details/{username}")
    Call<RegisterRequest> getUserDetails(@Path("username") String username);

    @GET("/manage/master/get")
    Call<InventoryMasterResponsePojo> getInventoryMaster();
}
