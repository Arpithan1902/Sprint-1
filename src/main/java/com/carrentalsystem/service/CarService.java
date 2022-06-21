package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.entity.User;

public interface CarService {

//	public Car getCarById(int carId);
	
	public List<Car> getAllCars();

	public List<Car> getCarByModel(String carModel);
	
	public List<Car> getCarBySeatingCapacity(int seatingCapacity);

	public List<Car> getCarByFuelType(String fuelType);

	
}
