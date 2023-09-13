package com.capstone.capstoneProject.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.capstoneProject.login.entities.WarehouseDetails;

@Repository
public interface WarehouseDetailsRepository extends JpaRepository<WarehouseDetails, Integer>{
	
	WarehouseDetails getWarehouseDetailsById(Integer id);

}
