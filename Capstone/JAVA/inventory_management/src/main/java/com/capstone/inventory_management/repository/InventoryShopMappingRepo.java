package com.capstone.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.inventory_management.entities.InventoryShopMapping;

@Repository
public interface InventoryShopMappingRepo extends CrudRepository<InventoryShopMapping, Integer>{

	List<InventoryShopMapping> findByShopId(Integer shopId);

	@Query("SELECT i from InventoryShopMapping i where i.shopId=:shopId and i.medId=:medId")
	InventoryShopMapping findByShopIdAndMedId(int shopId, Integer medId);

}
