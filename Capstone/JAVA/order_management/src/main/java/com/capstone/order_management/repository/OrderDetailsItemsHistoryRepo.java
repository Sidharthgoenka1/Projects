package com.capstone.order_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.order_management.entities.OrderDetailsItemsHistory;
import com.capstone.order_management.entities.OrderHistory;

@Repository
public interface OrderDetailsItemsHistoryRepo extends CrudRepository<OrderDetailsItemsHistory, Integer>{

	@Query("SELECT i from OrderDetailsItemsHistory i where i.orderId=:orderId and i.invId=:invId")
	List<OrderDetailsItemsHistory> findAllByInvIdAndOrderId(int invId, int orderId);


}
