package com.capstone.inventory_management.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.inventory_management.entities.AvailableDistForInventory;
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
import com.capstone.inventory_management.repository.InventoryDistMappingRepo;
import com.capstone.inventory_management.repository.InventoryMasterRepo;
import com.capstone.inventory_management.repository.InventoryShopMappingRepo;
import com.capstone.inventory_management.repository.ShopDistributorMapRepo;
import com.capstone.inventory_management.service.InventoryManagementService;

@Service
public class InventoryManagementServiceImpl implements InventoryManagementService {

	@Autowired
	InventoryDistMappingRepo inventoryDistMappingRepo;

	@Autowired
	InventoryShopMappingRepo inventoryShopMappingRepo;

	@Autowired
	InventoryMasterRepo inventoryMasterRepo;

	@Autowired
	ShopDistributorMapRepo shopDistributorMapRepo;

	@Override
	public InventoryMasterResponsePojo getAllInventoryMaster() {

		InventoryMasterResponsePojo inventoryResponsePojo = new InventoryMasterResponsePojo();
		try {
			List<InventoryMaster> list = (List<InventoryMaster>) inventoryMasterRepo.findAll();
			inventoryResponsePojo.setInventoryMasterList(list);
			inventoryResponsePojo.setStatusCode(200);
			inventoryResponsePojo.setStatusMessage("Success");
		} catch (Exception e) {
			inventoryResponsePojo.setStatusCode(-1);
			inventoryResponsePojo.setStatusMessage(e.getLocalizedMessage());
		}

		return inventoryResponsePojo;
	}

	@Override
	public InventoryDistributorMappingResponsePojo getInventoryByDistId(Integer distId) {
		// TODO Auto-generated method stub
		InventoryDistributorMappingResponsePojo response = new InventoryDistributorMappingResponsePojo();

		try {
			System.out.println("Fetching inventory for user -> "+distId);
			List<InventoryDistMapping> distMappingList = inventoryDistMappingRepo.findByDistId(distId);
			System.out.println("Inventory for the user -> {"+distMappingList.toString()+"}");
			response.setInventoryDistMappingList(distMappingList);
			response.setStatusCode(200);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setStatusMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public InventoryShopMappingResponsePojo getInventorybyShopId(Integer shopId) {
		// TODO Auto-generated method stub

		InventoryShopMappingResponsePojo response = new InventoryShopMappingResponsePojo();

		try {
			System.out.println("Fetching inventory for user -> "+shopId);
			List<InventoryShopMapping> shopMappingList = inventoryShopMappingRepo.findByShopId(shopId);
			System.out.println("Inventory for the user -> {"+shopMappingList.toString()+"}");
			response.setInventoryShopMappingList(shopMappingList);
			response.setStatusCode(200);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setStatusMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public ShopDistributorMapResponsePojo getAllShopsByDistId(Integer distId) {
		// TODO Auto-generated method stub
		ShopDistributorMapResponsePojo response = new ShopDistributorMapResponsePojo();
		try {

			List<ShopDistributorMap> shopDistMappingList = shopDistributorMapRepo.findByDistId(distId);
			response.setShopDistributorMapList(shopDistMappingList);
			response.setStatusCode(200);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setStatusMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public AvailableDistForInventoryWrapper getAvaialbleDistributorForInv(String invName) {
		// TODO Auto-generated method stub
		AvailableDistForInventoryWrapper response = new AvailableDistForInventoryWrapper();
		List<AvailableDistForInventory> dists = new ArrayList<>();

		List<InventoryMaster> inventoryMasterList = inventoryMasterRepo.findByName(invName);

		for (InventoryMaster i : inventoryMasterList) {
			List<InventoryDistMapping> invDistMappingList = inventoryDistMappingRepo.findByMedId(i.getId());
			for (InventoryDistMapping d : invDistMappingList) {
				if (d.getQuantity() > 0) {
					AvailableDistForInventory a = new AvailableDistForInventory();
					a.setDistId(d.getId() + "");
					a.setInvDesc(i.getDescription());
					a.setInvId(i.getId() + "");
					a.setInvName(i.getName());
					a.setQuantity(d.getQuantity());
					dists.add(a);
				}
			}
		}

		response.setAvailableInventoryResponseList(dists);
		response.setStatusCode(200);
		response.setStatusMessage("SUCCESS");
		System.out.println("Availaile inv -> {"+response.toString()+"}");
		return response;
	}

	@Override
	public String updateInventory(String shopId, List<UpdateInventoryPojo> request) {
		// TODO Auto-generated method stub
		System.out.println(
				"Updating inventory request from shop ->{" + shopId + "} with items -> {" + request.toString() + "}");
		try {
			for (UpdateInventoryPojo u : request) {
				System.out.println("Updating quantity for inv -> {" + u.getInvId() + "} for distributor -> {"
						+ u.getDistId() + "}");
				InventoryDistMapping d = inventoryDistMappingRepo.findByDistIdAndMedId(u.getDistId(), u.getInvId());
				if (d != null) {
					d.setQuantity(d.getQuantity() - u.getQuantity());
				} else {
					d = new InventoryDistMapping();
					d.setDistId(u.getDistId());
					d.setMedId(u.getInvId());
					d.setQuantity(u.getQuantity());
				}
				inventoryDistMappingRepo.save(d);

				System.out.println("Updating quantity for inv -> {" + u.getInvId() + "} for shop -> {" + shopId + "}");
				InventoryShopMapping s = inventoryShopMappingRepo.findByShopIdAndMedId(Integer.parseInt(shopId),
						u.getInvId());
				if (s != null) {
					s.setQuantity(s.getQuantity() + u.getQuantity());
				} else {
					s = new InventoryShopMapping();
					s.setMedId(u.getInvId());
					s.setQuantity(u.getQuantity());
					s.setShopId(Integer.parseInt(shopId));
				}
				inventoryShopMappingRepo.save(s);
			}

			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public String updateInvForDist(UpdateInventoryRequestPojo req) {
		// TODO Auto-generated method stub
		System.out.println("Updating inventory for req -> {"+req.toString()+"}");
		
		if(req.getItemId() == -1) {
			System.out.println("Adding new Inventory");
			InventoryMaster master = new InventoryMaster();
			master.setDescription(req.getItemDesc());
			master.setName(req.getItemName());
			master.setSalts(req.getItemSalts());
			master.setShortDescription(req.getShortDesc());
			
			InventoryMaster savedMaster = inventoryMasterRepo.save(master);
			req.setItemId(savedMaster.getId());
			System.out.println("New inventory saved -> {"+savedMaster.toString()+"}");
			
			InventoryDistMapping mapping = new InventoryDistMapping();
			mapping.setDistId(req.getWorkplaceId());
			mapping.setMedId(req.getItemId());
			mapping.setQuantity(req.getQty());
			inventoryDistMappingRepo.save(mapping);
			
		}else {
			System.out.println("Updating item for warehouse id -> {"+req.getWorkplaceId()+"}");
			InventoryDistMapping distMapping = inventoryDistMappingRepo.findByDistIdAndMedId(req.getWorkplaceId(), req.getItemId());
			distMapping.setQuantity(distMapping.getQuantity()+req.getQty());
			inventoryDistMappingRepo.save(distMapping);
		}
		
		System.out.println("Item updated");
		
		return "SUCCESS";
	}
}
