package com.capstone.capstoneProject.inventory.api;

import com.capstone.capstoneProject.inventory.model.InventoryDistributorMappingResponsePojo;
import com.capstone.capstoneProject.inventory.model.InventoryShopMappingResponsePojo;
import com.capstone.capstoneProject.inventory.model.UpdateInventoryRequestPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateInventoryPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InventoryManagementApi {

    @GET("/manage/shop/{shopId}")
    Call<InventoryShopMappingResponsePojo> getInventoryForShop(@Path("shopId") Integer shopId);

    @GET("/manage/distributor/{distId}")
    Call<InventoryDistributorMappingResponsePojo> getInventoryByDistId(@Path("distId") Integer distId);

    @POST("/manage/update/inventory/{shopId}")
    Call<String> updateInventory(@Path("shopId") String shopId, @Body List<UpdateInventoryPojo> request);

    @POST("/manage/update/inventory/dist")
    Call<String> updateDistInv(@Body UpdateInventoryRequestPojo req);
}
