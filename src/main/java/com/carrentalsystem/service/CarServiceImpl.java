package com.carrentalsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.exception.CarNotFoundException;
import com.carrentalsystem.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	

	@Autowired
	private CarRepository carRepository;
	
	
	@Override
	public Car getCarById(int carId) throws CarNotFoundException{
		Optional<Car> optionalCar=carRepository.findById(carId);
		
		if(optionalCar.isEmpty()) {
			throw new CarNotFoundException("Car Not found with ID:"+carId);
		}
		
		Car car=optionalCar.get();
		
		return car;
	}

	

	@Override
	public Car getCarByseatingCapacity(int seatingCapacity) throws CarNotFoundException{
		List<Car> optionalCar=carRepository.findAll();
		
		if(optionalCar.isEmpty()) {
			throw new CarNotFoundException("Car Not found with " +seatingCapacity+ " seats");
		}
		return optionalCar.get(seatingCapacity);
		
	}
	
	
	@Override
	public List<Car> getAllCars() {
		List<Car> allCars=carRepository.findAll();
		return allCars;
	}

	
	@Override
	public void deleteCar(int carId) {
		Optional<Car> optionalCar=carRepository.findById(carId);
		if(optionalCar.isEmpty()) {
			throw new CarNotFoundException("Car Not found with ID:"+carId);
		}
		carRepository.deleteById(carId);
	}


	@Override
	public Car updateCar(Car car) {
		Optional<Car> optionalCar=carRepository.findById(car.getCarId());
		if(optionalCar.isEmpty()) {
			throw new CarNotFoundException("Car not found with this id:"+car.getCarId());
		}
		Car updateCar=carRepository.save(car);
				
				return updateCar;
	}

	
	@Override
	public List<Car> getCarByModel(String carModel) {
		List<Car> cars=carRepository.findByCarModel(carModel);
		return cars;
	}

	
	@Override
	public List<Car> getCarByFuelType(String fuelType) {
		List<Car> fuelCars=carRepository.findByFuelType(fuelType);
		return fuelCars;
	}
	
	
	
	@Override
	public List<Car> getAllCarsWithinPriceRange(double lowerRenatlPrice, double upperRentalPrice) {
		List<Car> cars=carRepository.findAllCarsWithinPriceRange(upperRentalPrice, upperRentalPrice);
		
		
		return cars;
	}


}
