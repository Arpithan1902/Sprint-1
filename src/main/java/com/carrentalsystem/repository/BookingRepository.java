package com.carrentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carrentalsystem.entity.Booking;

public interface BookingRepository  extends JpaRepository<Booking, Integer>{
	
	@Query("select c from Booking c where c.user.userId= :uId")
	List<Booking> getAllBookingByUser(@Param("uId") int userId);

	
}
