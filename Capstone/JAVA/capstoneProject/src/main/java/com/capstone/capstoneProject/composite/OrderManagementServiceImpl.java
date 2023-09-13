package com.capstone.capstoneProject.composite;


import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.capstoneProject.composite.pojos.OrderDetailsItemHistoryPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateItemsPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateOrderRequest;
import com.capstone.capstoneProject.composite.wrappers.CreateOrderRequestWrapper;
import com.capstone.capstoneProject.composite.wrappers.OrderDataWrapper;
import com.capstone.capstoneProject.composite.wrappers.OrderDetailsItemsHistoryWrapper;
import com.capstone.capstoneProject.composite.wrappers.OrderHistoryPojo;
import com.capstone.capstoneProject.composite.wrappers.OrderHistoryResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.OrderItemsPojo;
import com.capstone.capstoneProject.composite.wrappers.OrderMasterResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.OrdersDataPojo;
import com.capstone.capstoneProject.login.entities.ShopDetails;
import com.capstone.capstoneProject.login.entities.UserDetailsMaster;
import com.capstone.capstoneProject.login.entities.WarehouseDetails;
import com.capstone.capstoneProject.login.repositories.ShopDetailsRepository;
import com.capstone.capstoneProject.login.repositories.UserDetailsMasterRepository;
import com.capstone.capstoneProject.login.repositories.WarehouseDetailsRepository;

@Service
public class OrderManagementServiceImpl implements OrderManagementService{

	private final OrderManagementFeignClient orderManagementFeignClient;
	
	@Autowired
	UserDetailsMasterRepository userDetailsMasterRepository;

	@Autowired
	WarehouseDetailsRepository warehouseDetailsRepository;
	
	@Autowired
	ShopDetailsRepository shopDetailsRepository;

    public OrderManagementServiceImpl(OrderManagementFeignClient orderManagementFeignClient) {
        this.orderManagementFeignClient = orderManagementFeignClient;
    }

	@Override
	public OrderHistoryResponsePojo getOrderDetails(Integer orderId) {
		// TODO Auto-generated method stub
		try {
			OrderHistoryResponsePojo response = orderManagementFeignClient.getOrderDetailsByOrderId(orderId);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}

	@Override
	public OrderMasterResponsePojo getOrderMaster(Integer orderId) {
		// TODO Auto-generated method stub
		try {
			OrderMasterResponsePojo response = orderManagementFeignClient.getOrderMaster(orderId);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}

	@Override
	public String createOrder(CreateOrderRequestWrapper request) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Creating order for request -> {"+request.toString()+"}");
			String response = orderManagementFeignClient.createOrder(request);
			System.out.println("Response received -> {"+response+"}");
			return response;
		}catch (Exception e) {
			
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

	@Override
	public String updateOrder(UpdateOrderRequest request) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Updating order");
			return orderManagementFeignClient.updateOrder(request);
		}catch( Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

	@Override
	public OrderDataWrapper getOrdersForUser(String userType, String workplaceId) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Going to OM");
			OrderDataWrapper wrapper = orderManagementFeignClient.getOrdersForUser(userType, workplaceId);
			
			for(OrdersDataPojo o : wrapper.getMessage()) {
//				for(OrderHistoryPojo h : o.getHistory()) {
//					h.setUserName(userDetailsMasterRepository.getUserById(h.getUpdatedBy()).getName());
//				}
//				
				for(OrderItemsPojo i : o.getItems()) {
					UserDetailsMaster userVal = userDetailsMasterRepository.getUserById(i.getDistId());
					WarehouseDetails w = warehouseDetailsRepository.getWarehouseDetailsById(userVal.getWorkplaceId());
					
					i.setDistName(userVal.getName());
					i.setwName(w.getWarehouseName());
					i.setwAddress(w.getWarehouseAddress());
					i.setwId(w.getId());
				}
				
				ShopDetails s = shopDetailsRepository.getShopDetailsById(o.getShopId());
				o.setShopName(s.getShopName());
				o.setShopAddress(s.getShopAddress());
			}
			System.out.println("Sending wrapper response-> {"+wrapper.toString()+"}");
			return wrapper;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	@Override
	public String getDummy() {
		URL url;
		try {
			System.out.println("Checking");
//			url = new URL("http://localhost:8081/inventory/get/dummy/inv");
//			 HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		        con.setRequestMethod("GET");
//
//		        BufferedReader in = new BufferedReader(
//		            new InputStreamReader(con.getInputStream()));
//		        String inputLine;
//		        StringBuffer content = new StringBuffer();
//		        while ((inputLine = in.readLine()) != null) {
//		            content.append(inputLine);
//		        }
//		        in.close();
//
//		        con.disconnect();
//		        return content.toString();
			
			return orderManagementFeignClient.getDummy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateOrderItems(List<UpdateItemsPojo> request) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Updating order items");
			return orderManagementFeignClient.updateOrderItems(request);
		}catch( Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

	@Override
	public OrderDetailsItemsHistoryWrapper getItemHistory(String invId, String orderId) {
		// TODO Auto-generated method stub
		try {
			OrderDetailsItemsHistoryWrapper wrapper = orderManagementFeignClient.getItemsHistory(invId, orderId);
			for(OrderDetailsItemHistoryPojo h : wrapper.getMessage()) {
				h.setUsername(userDetailsMasterRepository.getUserById(h.getUpdatedBy()).getName());
			}
			System.out.println("Sending reponse for item histroy -> {"+wrapper.toString()+"}");
			wrapper.setStatus("SUCCESS");
			wrapper.setStatusCode(200);
			return wrapper;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
