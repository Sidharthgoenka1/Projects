package com.capstone.capstoneProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.capstoneProject.composite.InventoryManagementService;
import com.capstone.capstoneProject.composite.OrderManagementService;
import com.capstone.capstoneProject.composite.pojos.UpdateInventoryPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateInventoryRequestPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateItemsPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateOrderRequest;
import com.capstone.capstoneProject.composite.wrappers.*;

@RestController
@RequestMapping("/manage")
public class CompositeController {
	
	@Autowired
	InventoryManagementService inventoryManagementService;
	
	@Autowired
	OrderManagementService orderManagementService;
	
	@GetMapping("/master/get")
	public InventoryMasterResponsePojo getAllInventoryMaster() {
		return inventoryManagementService.getAllInventoryMaster();
	}
	
	@GetMapping("/distributor/{distId}")
	public InventoryDistributorMappingResponsePojo getInventoryByDistId(@PathVariable Integer distId) {
		return inventoryManagementService.getInventoryForDist(distId);
	}
	
	@GetMapping("/shop/{shopId}")
	public InventoryShopMappingResponsePojo getInventoryByShopId(@PathVariable Integer shopId) {
		return inventoryManagementService.getInventoryForShop(shopId);
	}

	@GetMapping("/shops/{distId}")
	public ShopDistributorMapResponsePojo getAllShopsForDist(@PathVariable Integer distId) {
		return inventoryManagementService.getAllShopsByDistId(distId);
	}
	
	@GetMapping("/order/details/{orderId}")
	public OrderHistoryResponsePojo getOrderDetailsById(@PathVariable Integer orderId) {
		return orderManagementService.getOrderDetails(orderId);
	}
	
	@PostMapping("/order/master/{orderId}")
	public OrderMasterResponsePojo getOrderMaster(@PathVariable Integer orderId) {
		return orderManagementService.getOrderMaster(orderId);
	}
	
	@PostMapping("/inventories/{invName}")
	public AvailableDistForInventoryWrapper getDistForInventory(@PathVariable String invName) {
		return inventoryManagementService.getDistForInventory(invName);
	}
	
	@PostMapping("/order/create")
	public String createOrder(@RequestBody CreateOrderRequestWrapper request) {
		return orderManagementService.createOrder(request);
	}
	
	@PostMapping("/order/update")
	public String updateOrder(@RequestBody UpdateOrderRequest request) {
		return orderManagementService.updateOrder(request);
	}
	
	@PostMapping("/order/forUser/{userType}/{workplaceId}")
	public OrderDataWrapper getOrdersForUser(@PathVariable String userType,@PathVariable String workplaceId) {
		return orderManagementService.getOrdersForUser(userType, workplaceId);
	}
	
	@GetMapping("/get/dummy/inv")
	public String getdummyinv(){
		System.out.println("here we are");
		return inventoryManagementService.getDummy();
	}
	
	@GetMapping("/get/dummy/ord")
	public String getdummyord(){
		System.out.println("here we are");
		return orderManagementService.getDummy();
	}
	
	@PostMapping("/update/inventory/{shopId}")
	public String updateInventory(@PathVariable String shopId,@RequestBody List<UpdateInventoryPojo> request) {
		return inventoryManagementService.updateInventory(shopId, request);
	}
	
	@PostMapping("/order/update/items")
	public String updateOrderItems(@RequestBody List<UpdateItemsPojo> request) {
		return orderManagementService.updateOrderItems(request);
	}
	
	@PostMapping("/order/details/history/{invId}/{orderId}")
	public OrderDetailsItemsHistoryWrapper getItemsHistory(@PathVariable String invId, @PathVariable String orderId) {
		return orderManagementService.getItemHistory(invId, orderId);
	}
	
	@PostMapping("/update/inventory/dist")
	public String updateDistInv(@RequestBody UpdateInventoryRequestPojo req) {
		return inventoryManagementService.updateInventoryForDist(req);
	}
}
