package com.capstone.capstoneProject.composite;

import java.util.List;

import com.capstone.capstoneProject.composite.pojos.UpdateInventoryPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateInventoryRequestPojo;
import com.capstone.capstoneProject.composite.wrappers.AvailableDistForInventoryWrapper;
import com.capstone.capstoneProject.composite.wrappers.InventoryDistributorMappingResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.InventoryMasterResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.InventoryShopMappingResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.ShopDistributorMapResponsePojo;

public interface InventoryManagementService {

	InventoryMasterResponsePojo getAllInventoryMaster();
	
	InventoryShopMappingResponsePojo getInventoryForShop(Integer shopId);
	
	InventoryDistributorMappingResponsePojo getInventoryForDist(Integer distId);
	
	ShopDistributorMapResponsePojo getAllShopsByDistId(Integer distId);
	
	AvailableDistForInventoryWrapper getDistForInventory(String invName);
	
	String getDummy();
	
	String updateInventory(String shopId, List<UpdateInventoryPojo> request);
	
	String updateInventoryForDist(UpdateInventoryRequestPojo req);
}
