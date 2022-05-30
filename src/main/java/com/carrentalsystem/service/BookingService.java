package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.entity.Booking;

public interface BookingService {
	
	public Booking saveBooking(Booking booking);
	
	public Booking updateBooking(Booking booking);
	
	public void deleteBooking(int bookingId);

	public List<Booking> getAllBooking();
	
	public Booking getBookingById(int bookingId);
}
