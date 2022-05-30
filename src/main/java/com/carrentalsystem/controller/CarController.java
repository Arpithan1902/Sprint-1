package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.service.CarService;

@RestController
public class CarController {
	
	@Autowired
	private CarService carService;
	
	
	@GetMapping("/car/all")
	public List<Car> fetchAllCars(){
		List<Car> carList=carService.getAllCars();
		return carList;
		
	}

	@GetMapping("/car/find/{carId}")
	public ResponseEntity<Object> fetchCarById(@PathVariable("carId")int carId){
		ResponseEntity<Object> responseEntity=null;
		Car car=carService.getCarById(carId);
		responseEntity=new ResponseEntity<>(car,HttpStatus.OK);
		return responseEntity;
		
	}
	
	@GetMapping("/car/seating/{seatingCapacity}")
	public ResponseEntity<Object> fetchCarBySeatingCapacity(@PathVariable("seatingCapacity")int seatingCapacity){
		ResponseEntity<Object> responseEntity=null;
		Car car=carService.getCarByseatingCapacity(seatingCapacity);
		responseEntity=new ResponseEntity<>(car,HttpStatus.OK);
		return responseEntity;
	}
	
	
	
	@GetMapping("/car/bymodel/{modelname}")
	public List<Car> fetchCarByModel(@PathVariable("modelname") String carModel){
		List<Car> cars=carService.getCarByModel(carModel);
		return cars;
	}
	
	@GetMapping("/car/byfuelType/{fuel}")
	public List<Car> fetchCarByFuelType(@PathVariable("fuel")String fuelType){
		List<Car> cars=carService.getCarByFuelType(fuelType);
		return cars;
		
	}
	
	@GetMapping("/car/byrentalprice/{lowerPrice}/{upperPrice}")
	public List<Car> fetchCarByRentalPriceRange(@PathVariable("lowerPrice")double lowerRentalPrice,@PathVariable("upperPrice")double upperRentalPrice){   
		List<Car> cars=carService.getAllCarsWithinPriceRange(lowerRentalPrice, upperRentalPrice);
		return cars;
	}
	
	
	@DeleteMapping("/car/delete/{carId}")
	public ResponseEntity<String> removeCar(@PathVariable("carId") int carId){
		carService.deleteCar(carId);
		ResponseEntity<String> responseEntity=new ResponseEntity<>("Car deleted uccessfully",HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/car/update")
	public ResponseEntity<Car> modifyCar(@RequestBody Car car){
		Car updateCar=carService.updateCar(car);
		ResponseEntity<Car> responseEntity=new ResponseEntity<>(updateCar,HttpStatus.OK);
		return responseEntity;
		
				
	}
	
	
	
	
}



