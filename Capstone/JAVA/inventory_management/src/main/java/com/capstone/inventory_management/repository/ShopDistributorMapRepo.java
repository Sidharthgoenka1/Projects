package com.capstone.inventory_management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.inventory_management.entities.ShopDistributorMap;

@Repository
public interface ShopDistributorMapRepo extends CrudRepository<ShopDistributorMap, Integer>{

	List<ShopDistributorMap> findByDistId(Integer distId);

}
