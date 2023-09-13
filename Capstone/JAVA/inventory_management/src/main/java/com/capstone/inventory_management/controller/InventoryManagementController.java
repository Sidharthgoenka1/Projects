package com.capstone.inventory_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.inventory_management.entities.InventoryDistMapping;
import com.capstone.inventory_management.entities.InventoryMaster;
import com.capstone.inventory_management.entities.InventoryShopMapping;
import com.capstone.inventory_management.entities.ShopDistributorMap;
import com.capstone.inventory_management.entities.pojo.UpdateInventoryPojo;
import com.capstone.inventory_management.entities.pojo.UpdateInventoryRequestPojo;
import com.capstone.inventory_management.entities.wrappers.AvailableDistForInventoryWrapper;
import com.capstone.inventory_management.entities.wrappers.InventoryDistributorMappingResponsePojo;
import com.capstone.inventory_management.entities.wrappers.InventoryMasterResponsePojo;
import com.capstone.inventory_management.entities.wrappers.InventoryShopMappingResponsePojo;
import com.capstone.inventory_management.entities.wrappers.ShopDistributorMapResponsePojo;
import com.capstone.inventory_management.service.InventoryManagementService;

@RestController
@RequestMapping("/inventory")
public class InventoryManagementController {

	@Autowired
	InventoryManagementService inventoryManagementService;
	
	@GetMapping("/master/get")
	public InventoryMasterResponsePojo getAllInventoryMaster() {
		return inventoryManagementService.getAllInventoryMaster();
	}

	@GetMapping("/distributor/{distId}")
	public InventoryDistributorMappingResponsePojo getInventoryByDistId(@PathVariable Integer distId) {
		return inventoryManagementService.getInventoryByDistId(distId);
	}
	
	@GetMapping("/shop/{shopId}")
	public InventoryShopMappingResponsePojo getInventoryByShopId(@PathVariable Integer shopId) {
		return inventoryManagementService.getInventorybyShopId(shopId);
	}
	
	@GetMapping("/shops/{distId}")
	public ShopDistributorMapResponsePojo getAllShopsForDist(@PathVariable Integer distId) {
		return inventoryManagementService.getAllShopsByDistId(distId);
	}
	
	@PostMapping("/search/{invname}")
	public AvailableDistForInventoryWrapper getInventoriesByname(@PathVariable String invname) {
		return inventoryManagementService.getAvaialbleDistributorForInv(invname);
	}
	
	@PostMapping("/update/inventory/{shopId}")
	public String updateInventory(@PathVariable String shopId, @RequestBody List<UpdateInventoryPojo> request) {
		return inventoryManagementService.updateInventory(shopId, request);
	}
	
	@GetMapping("/get/dummy/inv")
	public String getstr() {
		return "connected 2";
	}
	
	@PostMapping("/update/dist/inv")
	public String updateDistInventory(@RequestBody UpdateInventoryRequestPojo request) {
		return inventoryManagementService.updateInvForDist(request);
	}
}
