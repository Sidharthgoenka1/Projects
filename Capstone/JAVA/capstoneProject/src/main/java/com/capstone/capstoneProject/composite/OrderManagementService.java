package com.capstone.capstoneProject.composite;


import java.util.List;

import com.capstone.capstoneProject.composite.pojos.UpdateItemsPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateOrderRequest;
import com.capstone.capstoneProject.composite.wrappers.CreateOrderRequestWrapper;
import com.capstone.capstoneProject.composite.wrappers.OrderDataWrapper;
import com.capstone.capstoneProject.composite.wrappers.OrderDetailsItemsHistoryWrapper;
import com.capstone.capstoneProject.composite.wrappers.OrderHistoryResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.OrderMasterResponsePojo;

public interface OrderManagementService {

	OrderHistoryResponsePojo getOrderDetails(Integer orderId);
	
	OrderMasterResponsePojo getOrderMaster(Integer orderId);
	
	String createOrder(CreateOrderRequestWrapper request);
	
	String updateOrder(UpdateOrderRequest request);
	
	OrderDataWrapper getOrdersForUser(String userType,String workplaceId);
	
	String getDummy();
	
	String updateOrderItems(List<UpdateItemsPojo> request);
	
	OrderDetailsItemsHistoryWrapper getItemHistory(String invId, String orderId);
	
}
