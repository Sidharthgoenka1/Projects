package com.capstone.capstoneProject.composite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.capstoneProject.composite.pojos.AvailableDistForInventory;
import com.capstone.capstoneProject.composite.pojos.UpdateInventoryPojo;
import com.capstone.capstoneProject.composite.pojos.UpdateInventoryRequestPojo;
import com.capstone.capstoneProject.composite.wrappers.AvailableDistForInventoryWrapper;
import com.capstone.capstoneProject.composite.wrappers.InventoryDistributorMappingResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.InventoryMasterResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.InventoryShopMappingResponsePojo;
import com.capstone.capstoneProject.composite.wrappers.ShopDistributorMapResponsePojo;
import com.capstone.capstoneProject.login.UserLoginService;
import com.capstone.capstoneProject.login.entities.UserDetailsMaster;
import com.capstone.capstoneProject.login.entities.WarehouseDetails;
import com.capstone.capstoneProject.login.repositories.UserDetailsMasterRepository;
import com.capstone.capstoneProject.login.repositories.WarehouseDetailsRepository;

@Service
public class InventoryManagementServiceImpl implements InventoryManagementService {

	private final InventoryManagementFeignClient inventoryManagementFeignClient;

	@Autowired
	UserDetailsMasterRepository userDetailsMasterRepository;

	@Autowired
	WarehouseDetailsRepository warehouseDetailsRepository;

	public InventoryManagementServiceImpl(InventoryManagementFeignClient inventoryManagementFeignClient) {
		this.inventoryManagementFeignClient = inventoryManagementFeignClient;
	}

	@Override
	public InventoryMasterResponsePojo getAllInventoryMaster() {
		try {
			InventoryMasterResponsePojo r = inventoryManagementFeignClient.getAllInventoryMaster();
			System.out.println("here it is -> " + r.toString());

			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public InventoryShopMappingResponsePojo getInventoryForShop(Integer shopId) {
		// TODO Auto-generated method stub
		try {
			InventoryShopMappingResponsePojo response = inventoryManagementFeignClient.getInventoryByShopId(shopId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public InventoryDistributorMappingResponsePojo getInventoryForDist(Integer distId) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Getting inventory fpr dist - "+distId);
			InventoryDistributorMappingResponsePojo response = inventoryManagementFeignClient
					.getInventoryByDistId(distId);
			System.out.println("Returning inventory data -> {"+response.toString()+"}");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ShopDistributorMapResponsePojo getAllShopsByDistId(Integer distId) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Getting inventory for shop - "+distId);
			ShopDistributorMapResponsePojo response = inventoryManagementFeignClient.getShopsByDist(distId);
			System.out.println("Returning inventory data -> {"+response.toString()+"}");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AvailableDistForInventoryWrapper getDistForInventory(String invName) {
		// TODO Auto-generated method stub
		try {

			System.out.println("Searching for inv name -> {" + invName + "}");

			AvailableDistForInventoryWrapper wrapper = inventoryManagementFeignClient
					.getDistForInventoriesByname(invName);

			System.out.println("Response from im service -> {" + wrapper.toString() + "}");

			AvailableDistForInventoryWrapper response = new AvailableDistForInventoryWrapper();

			if (wrapper.getAvailableInventoryResponseList().size() > 0) {
				response.setAvailableInventoryResponseList(new ArrayList<>());
				for (AvailableDistForInventory d : wrapper.getAvailableInventoryResponseList()) {
					UserDetailsMaster user = userDetailsMasterRepository
							.findUserByIdAndUserType(Integer.parseInt(d.getDistId()), "D");
					if (user != null) {
						WarehouseDetails warehouse = warehouseDetailsRepository
								.getWarehouseDetailsById(user.getWorkplaceId());
						if (warehouse != null) {
							d.setDistAddress(user.getAddress());
							d.setDistContact(user.getContactNo());
							d.setDistName(user.getName());
							d.setEmail(user.getEmail());
							d.setwAddress(warehouse.getWarehouseAddress());
							d.setwContact(warehouse.getWarehouseContact());
							d.setwId(warehouse.getId());
							d.setwName(warehouse.getWarehouseName());

							response.getAvailableInventoryResponseList().add(d);

						}
					}
				}
			} else {
				response.setAvailableInventoryResponseList(wrapper.getAvailableInventoryResponseList());
			}

			response.setStatusCode(wrapper.getStatusCode());
			response.setStatusMessage(wrapper.getStatusMessage());
			System.out.println(" Sending inventory data for inv name -> {" + invName + "}\n response is - {"
					+ response.toString() + "}");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getDummy() {
		// TODO Auto-generated method stub
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

			return inventoryManagementFeignClient.getDummy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String updateInventory(String shopId, List<UpdateInventoryPojo> request) {
		// TODO Auto-generated method stub
		try {

			return inventoryManagementFeignClient.updateInventory(shopId, request);

		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public String updateInventoryForDist(UpdateInventoryRequestPojo req) {
		// TODO Auto-generated method stub
		try {
			return inventoryManagementFeignClient.updateInventoryForDist(req);			
		}catch(Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
}
