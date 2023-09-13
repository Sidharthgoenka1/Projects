package com.capstone.order_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.order_management.entities.OrderMaster;

@Repository
public interface OrderMasterRepo extends CrudRepository<OrderMaster, Integer>{

	OrderMaster findByOrderId(Integer orderId);

//	@Query("UPDATE OrderMaster o set o.statusId = :statusId WHERE o.orderId = :orderId")
//	OrderMaster updateStatusIdByOrderId(@Param("orderId")Integer orderId,@Param("statusId") Integer statusId);

	@Query("SELECT o FROM OrderMaster o where o.shopId = :shopId")
	List<OrderMaster> findByShopId(@Param("shopId") Integer shopId);

}
