package com.paypal.bfs.test.bookingserv.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

@Repository
@Component
public interface BookingservRepository extends  CrudRepository<Booking, Integer> 
{
	
	@Query(nativeQuery = true, value = "select * from booking")
	List<Booking> findAllBookings();

}
