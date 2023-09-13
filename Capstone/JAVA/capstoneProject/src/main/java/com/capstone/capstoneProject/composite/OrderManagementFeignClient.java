package com.capstone.capstoneProject.composite;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.capstoneProject.composite.pojos.UpdateItemsPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateOrderRequest;
import com.capstone.capstoneProject.composite.wrappers.*;


@FeignClient(name = "order-core", url = "http://localhost:8082/")
public interface OrderManagementFeignClient {

	@GetMapping("/order/details/{orderId}")
	public OrderHistoryResponsePojo getOrderDetailsByOrderId(@PathVariable Integer orderId);
	
	@GetMapping("/order/master/{orderId}")
	public OrderMasterResponsePojo getOrderMaster(@PathVariable Integer orderId); 
	
	@PostMapping("/order/create")
	public String createOrder(@RequestBody CreateOrderRequestWrapper request);
	
	@PostMapping("/order/update")
	public String updateOrder(@RequestBody UpdateOrderRequest request);
	
	@PostMapping("/order/forUser/{userType}/{workplaceId}")
	public OrderDataWrapper getOrdersForUser(@PathVariable String userType,@PathVariable String workplaceId);
	
	@GetMapping("/order/get/dummy/ord")
	public String getDummy();

	@PostMapping("/order/update/items")
	public String updateOrderItems(List<UpdateItemsPojo> request);
	
	@PostMapping("/order/details/history/{invId}/{orderId}")
	public OrderDetailsItemsHistoryWrapper getItemsHistory(@PathVariable String invId, @PathVariable String orderId);
}