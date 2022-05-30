package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.Car;
import com.carrentalsystem.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking/save")
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking){
		
		Booking newBooking = bookingService.saveBooking(booking);
		ResponseEntity<Booking> responseEntity = new ResponseEntity<>(newBooking,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@DeleteMapping("/booking/delete/{bookingId}")
	public ResponseEntity<String> removeBooking(@PathVariable("bookingId") int bookingId) {
		
		bookingService.deleteBooking(bookingId);		
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Booking Cancelled Successfully.", HttpStatus.OK);
		return responseEntity;		
	}
	
	@PutMapping("/booking/update")
	public ResponseEntity<Booking> modifyProduct(@RequestBody Booking booking) {
		
		Booking updatedBooking = bookingService.updateBooking(booking);	
		ResponseEntity<Booking> responseEntity = new ResponseEntity<>(updatedBooking,HttpStatus.OK);
		return responseEntity;
	}
	


	@GetMapping("/booking/all")
	public List<Booking> fetchAllBookings(){
		List<Booking> bookingList=bookingService.getAllBooking();
		return bookingList;
		
	}
	
	@GetMapping("booking/find/{bookingId}")
	public ResponseEntity<Object> fetchBookingById(@PathVariable("bookingId") int booingId){
		ResponseEntity<Object> responseEntity=null; 
		Booking booking=bookingService.getBookingById(booingId);
		responseEntity=new ResponseEntity<>(booking,HttpStatus.OK);
		return responseEntity;
	}
	
	

}