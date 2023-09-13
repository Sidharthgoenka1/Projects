package com.capstone.order_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.order_management.entities.OrderDetailsItems;


@Repository
public interface OrderDetailsItemRepository extends JpaRepository<OrderDetailsItems, Integer>{

	@Query("SELECT i FROM OrderDetailsItems i where i.orderId=:orderId")
	List<OrderDetailsItems> findByOrderId(@Param("orderId") Integer orderId);

	@Query("SELECT i from OrderDetailsItems i where i.distId=:distId")
	List<OrderDetailsItems> findByDistId(@Param("distId") int distId);

	@Query("SELECT i from OrderDetailsItems i where i.distId=:distId and i.orderId=:orderId and i.invId=:invId")
	OrderDetailsItems findByOrderIdAndInvIdAndDistId(@Param("orderId") Integer orderId,@Param("invId") Integer invId,@Param("distId") Integer distId);


}
