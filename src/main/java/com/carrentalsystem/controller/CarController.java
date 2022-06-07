package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/car/all")
	public List<Car> fetchAllCars() {
		List<Car> carList = carService.getAllCars();
		return carList;

	}

	@GetMapping("/car/seating/{seatingcapacity}")
	public List<Car> fetchCarBySeatingCapacity(@PathVariable("seatingcapacity") int seatingCapacity) {

		List<Car> carSeating = carService.getCarBySeatingCapacity(seatingCapacity);

		return carSeating;

	}

	@GetMapping("/car/bymodel/{modelname}")
	public List<Car> fetchCarByModel(@PathVariable("modelname") String carModel) {
		List<Car> cars = carService.getCarByModel(carModel);
		return cars;
	}

	@GetMapping("/car/byfuelType/{fuel}")
	public List<Car> fetchCarByFuelType(@PathVariable("fuel") String fuelType) {
		List<Car> cars = carService.getCarByFuelType(fuelType);
		return cars;

	}

}
