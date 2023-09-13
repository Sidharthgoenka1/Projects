package com.capstone.capstoneProject.composite;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.capstoneProject.composite.pojos.UpdateInventoryPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateInventoryRequestPojo;
import com.capstone.capstoneProject.composite.wrappers.*;



@FeignClient(name = "inventory-core", url = "http://localhost:8081/")
public interface InventoryManagementFeignClient {

	@GetMapping("/inventory/master/get")
	public InventoryMasterResponsePojo getAllInventoryMaster();
		
	@GetMapping("/inventory/distributor/{distId}")
	public InventoryDistributorMappingResponsePojo getInventoryByDistId(@PathVariable Integer distId);
	
	@GetMapping("/inventory/shop/{shopId}")
	public InventoryShopMappingResponsePojo getInventoryByShopId(@PathVariable Integer shopId);
	
	@GetMapping("/inventory/shops/{distId}")
	public ShopDistributorMapResponsePojo getShopsByDist(@PathVariable Integer distId);

	@PostMapping("/inventory/search/{invname}")
	public AvailableDistForInventoryWrapper getDistForInventoriesByname(@PathVariable String invname); 
	
	@GetMapping("/inventory/get/dummy/inv")
	public String getDummy();
	
	@PostMapping("/inventory/update/inventory/{shopId}")
	public String updateInventory(@PathVariable String shopId,@RequestBody List<UpdateInventoryPojo> request);
	
	@PostMapping("/inventory/update/dist/inv")
	public String updateInventoryForDist(@RequestBody UpdateInventoryRequestPojo req);
}