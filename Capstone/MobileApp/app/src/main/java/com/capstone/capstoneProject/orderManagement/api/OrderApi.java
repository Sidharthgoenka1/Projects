package com.capstone.capstoneProject.orderManagement.api;


import com.capstone.capstoneProject.orderManagement.model.AvailableDistForInventoryWrapper;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestWrapper;
import com.capstone.capstoneProject.orderManagement.model.OrderDataWrapper;
import com.capstone.capstoneProject.orderManagement.model.OrderDetailsItemsHistoryWrapper;
import com.capstone.capstoneProject.orderManagement.model.UpdateInventoryPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateItemsPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateOrderRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {

    @POST("/manage/order/create")
    Call<String> createOrder(@Body CreateOrderRequestWrapper request);

    @POST("/manage/order/update")
    Call<String> updateOrder(@Body UpdateOrderRequest request);

    @POST("/manage/inventories/{invName}")
    Call<AvailableDistForInventoryWrapper> searchItem(@Path("invName") String invName);

    @POST("/manage/order/forUser/{userType}/{workplaceId}")
    Call<OrderDataWrapper> getOrdersForUser(@Path("userType") String userType,@Path("workplaceId") String workplaceId);

    @POST("/manage/update/inventory/{shopId}")
    Call<String> updateInventory(@Path("shopId") String shopId, @Body List<UpdateInventoryPojo> request);

    @POST("/manage/order/update/items")
    Call<String> updateOrderItems(@Body List<UpdateItemsPojo> request);

    @POST("/manage/order/details/history/{invId}/{orderId}")
    Call<OrderDetailsItemsHistoryWrapper> getItemsHistory(@Path("invId") String invId, @Path("orderId") String orderId);
}
