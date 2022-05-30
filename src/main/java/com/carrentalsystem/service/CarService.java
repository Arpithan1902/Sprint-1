package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.entity.Car;


public interface CarService {
	
	public Car getCarById(int carId);
	
	public Car getCarByseatingCapacity(int seatingCapacity);
	
	public List<Car> getAllCars();
	
	public void deleteCar(int carId);
	
	public Car updateCar(Car car);
	
	public List<Car> getCarByModel(String carModel);
	
	public List<Car> getCarByFuelType(String fuelType);
	
	public List<Car> getAllCarsWithinPriceRange(double lowerRenatlPrice,double upperRentalPrice);
	
	

}
