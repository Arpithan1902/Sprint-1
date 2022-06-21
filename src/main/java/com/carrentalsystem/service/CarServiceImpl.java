package com.carrentalsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.entity.User;
import com.carrentalsystem.exception.CarNotFoundException;
import com.carrentalsystem.exception.UserNotFoundException;
import com.carrentalsystem.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;



	@Override
	public List<Car> getAllCars() {
		List<Car> allCars = carRepository.findAll();
		return allCars;
	}

	@Override
	public List<Car> getCarByModel(String carModel) {
		List<Car> cars = carRepository.findByCarModel(carModel);
		return cars;
	}

	@Override
	public List<Car> getCarByFuelType(String fuelType) {
		List<Car> fuelCars = carRepository.findByFuelType(fuelType);
		return fuelCars;
	}


	@Override
	public List<Car> getCarBySeatingCapacity(int seatingCapacity) {
		List<Car> carSeating= carRepository.findBySeatingCapacity(seatingCapacity);
		return carSeating;
	}

//	@Override
//	public Car getCarById(int carId); {
//		Optional<Car> optionalCar=carRepository.findById(carId);
//		if(optionalCar.isEmpty()) {
//			throw new CarNotFoundException("User Not existing with Id:" + carId);
//			
//		}
//		Car car=optionalCar.get();
//		return car;
//	}

}
