package com.capstone.order_management.service;



import java.util.List;

import com.capstone.order_management.entities.pojos.UpdateItemsPojo;
import com.capstone.order_management.entities.pojos.UpdateOrderRequest;
import com.capstone.order_management.wrapper.CreateOrderRequestWrapper;
import com.capstone.order_management.wrapper.OrderDataWrapper;
import com.capstone.order_management.wrapper.OrderDetailsItemsHistoryWrapper;
import com.capstone.order_management.wrapper.OrderHistoryResponsePojo;
import com.capstone.order_management.wrapper.OrderLineItemsWrapper;
import com.capstone.order_management.wrapper.OrderMasterResponsePojo;

public interface OrderManagementService {

	
	OrderHistoryResponsePojo getOrderDetailsById(Integer orderId);
	
	OrderMasterResponsePojo getOrderMaster(Integer orderId);
	
	String createOrder(CreateOrderRequestWrapper request);
	
	String updateOrder(UpdateOrderRequest request);
	
	OrderDataWrapper getOrdersForUser(String userType, String workplaceId);
	
	OrderLineItemsWrapper getOrderLineItems(String orderId, String userId, String userType);

	String updateOrderItems(List<UpdateItemsPojo> request);

	OrderDetailsItemsHistoryWrapper getItemHistory(String invId, String orderId);
}
