package com.capstone.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.inventory_management.entities.InventoryDistMapping;

@Repository
public interface InventoryDistMappingRepo extends CrudRepository<InventoryDistMapping, Integer>{

	List<InventoryDistMapping> findByDistId(Integer distId);

	List<InventoryDistMapping> findByMedId(Integer id);

	@Query("SELECT i from InventoryDistMapping i where i.distId=:distId and i.medId=:medId")
	InventoryDistMapping findByDistIdAndMedId(Integer distId, Integer medId);

}
