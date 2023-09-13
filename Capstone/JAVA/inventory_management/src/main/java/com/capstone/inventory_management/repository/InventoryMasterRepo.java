package com.capstone.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.inventory_management.entities.InventoryMaster;

@Repository
public interface InventoryMasterRepo extends JpaRepository<InventoryMaster, Integer>{

	List<InventoryMaster> findByName(String invName);

}
