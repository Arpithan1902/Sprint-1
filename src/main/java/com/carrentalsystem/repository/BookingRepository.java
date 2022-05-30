package com.carrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalsystem.entity.Booking;

public interface BookingRepository  extends JpaRepository<Booking, Integer>{
	

	
}
