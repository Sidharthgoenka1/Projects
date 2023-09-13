package com.capstone.order_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.order_management.entities.pojos.UpdateItemsPojo;
import com.capstone.order_management.entities.pojos.UpdateOrderRequest;
import com.capstone.order_management.service.OrderManagementService;
import com.capstone.order_management.wrapper.CreateOrderRequestWrapper;
import com.capstone.order_management.wrapper.OrderDataWrapper;
import com.capstone.order_management.wrapper.OrderDetailsItemsHistoryWrapper;
import com.capstone.order_management.wrapper.OrderHistoryResponsePojo;
import com.capstone.order_management.wrapper.OrderLineItemsWrapper;
import com.capstone.order_management.wrapper.OrderMasterResponsePojo;

@RestController
@RequestMapping("/order")
public class OrderManagementController {

	@Autowired
	OrderManagementService orderManagementService;

	@GetMapping("/details/{orderId}")
	public OrderHistoryResponsePojo getOrderDetailsByOrderId(@PathVariable Integer orderId) {
		return orderManagementService.getOrderDetailsById(orderId);
	}

	@GetMapping("/master/{orderId}")
	public OrderMasterResponsePojo getOrderMaster(@PathVariable Integer orderId) {
		return orderManagementService.getOrderMaster(orderId);
	}

	@PostMapping("/create")
	public String createOrder(@RequestBody CreateOrderRequestWrapper request) {
		return orderManagementService.createOrder(request);
	}

	@PostMapping("/forUser/{userType}/{workplaceId}")
	public OrderDataWrapper getOrdersForUser(@PathVariable String userType, @PathVariable String workplaceId) {
		return orderManagementService.getOrdersForUser(userType, workplaceId);
	}

	@PostMapping("/update")
	public String updateOrder(@RequestBody UpdateOrderRequest request) {
		return orderManagementService.updateOrder(request);
	}
	
	@PostMapping("/update/items")
	public String updateOrderItems(@RequestBody List<UpdateItemsPojo> request) {
		return orderManagementService.updateOrderItems(request);
	}	
	
	@GetMapping("/get/dummy/ord")
	public String getstr() {
		return "connected 1";
	}
	
	@PostMapping("/details/{orderId}/{userId}/{userType}")
	public OrderLineItemsWrapper getOrderLineItems(@PathVariable String orderId, @PathVariable String userId, @PathVariable String userType) {
		return null;
	}
	
	@PostMapping("/details/history/{invId}/{orderId}")
	public OrderDetailsItemsHistoryWrapper getItemsHistory(@PathVariable String invId, @PathVariable String orderId) {
		return orderManagementService.getItemHistory(invId, orderId);
	}
}
