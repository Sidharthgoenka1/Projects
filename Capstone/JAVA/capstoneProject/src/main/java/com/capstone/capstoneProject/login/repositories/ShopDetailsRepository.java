package com.capstone.capstoneProject.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.capstoneProject.login.entities.ShopDetails;

@Repository
public interface ShopDetailsRepository extends JpaRepository<ShopDetails, Integer>{
	
	ShopDetails getShopDetailsById(Integer id);

}
