package com.carrentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carrentalsystem.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
	
	 
	List<Car> findByCarModel(String carModel);
	
	List<Car> findByFuelType(String fuelType);
	
	List<Car> findBySeatingCapacity(int seatingcapacity);

	
	@Query("select c from Car c where c.rentalPrice between :lPrice and :uPrice")
	List<Car> findAllCarsWithinPriceRange(@Param("lPrice")double lowerRentalPrice,@Param("uPrice")double upperRentalPrice);

}
