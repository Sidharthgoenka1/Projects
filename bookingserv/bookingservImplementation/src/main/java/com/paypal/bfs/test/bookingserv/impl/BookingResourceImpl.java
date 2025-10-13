package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import com.paypal.bfs.test.bookingserv.dao.*;

@RestController
public class BookingResourceImpl implements BookingResource {

	@Autowired
	BookingservRepository bookingservRepository;
	
	//validating the request Body
    @Override
    public ResponseEntity<Booking> create(@Valid Booking booking) {
    	Booking bookingSaved = bookingservRepository.save(booking);
    	if(bookingSaved==null)
    		return  new ResponseEntity<Booking>(bookingSaved, HttpStatus.INTERNAL_SERVER_ERROR) ;
    	else
    		return  new ResponseEntity(bookingSaved, HttpStatus.OK) ;
    }
    
    @Override
    public ResponseEntity<Booking> getAllBookings() {
    	List<Booking> list= bookingservRepository.findAllBookings();
    	if(list==null)
    	{
    		return  new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR) ;
    	}else
    	{
    		return  new ResponseEntity(list, HttpStatus.OK) ;
    	}
    }
    
}
