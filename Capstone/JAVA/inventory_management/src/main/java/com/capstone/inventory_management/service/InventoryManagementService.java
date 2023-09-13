package com.capstone.inventory_management.service;

import java.util.List;
import java.util.Optional;

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

public interface InventoryManagementService {

	InventoryMasterResponsePojo getAllInventoryMaster();
	
	InventoryDistributorMappingResponsePojo getInventoryByDistId(Integer distId);
	
	InventoryShopMappingResponsePojo getInventorybyShopId(Integer shopId);
	
	ShopDistributorMapResponsePojo getAllShopsByDistId(Integer distId);
	
	AvailableDistForInventoryWrapper getAvaialbleDistributorForInv(String invName);
	
	String updateInventory(String shopId, List<UpdateInventoryPojo> request);
	
	String updateInvForDist(UpdateInventoryRequestPojo req);
}
