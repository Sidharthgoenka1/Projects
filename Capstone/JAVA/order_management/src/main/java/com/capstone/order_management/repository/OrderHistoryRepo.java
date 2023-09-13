package com.capstone.order_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.order_management.entities.OrderHistory;

@Repository
public interface OrderHistoryRepo extends CrudRepository<OrderHistory, Integer>{

	@Query("SELECT i FROM OrderHistory i where i.orderId= :orderId")
	List<OrderHistory> findByOrderId(Integer orderId);

}
