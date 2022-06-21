package com.carrentalsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.dto.ForgetPasswordRequest;
import com.carrentalsystem.dto.LoginRequestDTO;
import com.carrentalsystem.dto.LoginResponseDTO;
import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.User;
import com.carrentalsystem.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")

public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponseDTO> signin(@RequestBody LoginRequestDTO loginRequest) {
		User user = userService.doLogin(loginRequest.getEmail(), loginRequest.getPassword());

		LoginResponseDTO loginResponse = new LoginResponseDTO();
		loginResponse.setUserId(user.getUserId());
		loginResponse.setName(user.getName());
		loginResponse.setPhone(user.getPhone());
		loginResponse.setAddress(user.getAddress());

		ResponseEntity<LoginResponseDTO> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
		return responseEntity;

	}

	@PutMapping("/user/forgetPassword")
	public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordReq) {
		String email = forgetPasswordReq.getEmail();
		long phone = forgetPasswordReq.getPhone();
		String newPassword = forgetPasswordReq.getNewPassword();
		userService.forgetPassword(email, phone, newPassword);
		return new ResponseEntity<>("Password updated Successfully!", HttpStatus.OK);
	}

	@PostMapping("/user/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User newUser = userService.saveUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
		return responseEntity;

	}

	@GetMapping("/user/booking/{userId}")
	public List<Booking> fetchAllBookingByUser(@PathVariable("userId") int userId) {
		List<Booking> bookings = userService.getAllBookingByUserId(userId);
		return bookings;
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> fetchUserDetails(@PathVariable("userId") int userId) {
		User user = userService.getUserById(userId);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/user/all")
	public List<User> fetchAllUsers() {
		List<User> userLsit = userService.getAllUsers();
		return userLsit;
	}

	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable("userId") int userId) {
		userService.deleteUSer(userId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("User deleted successfully!!", HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/user/update")
	public ResponseEntity<User> modifyUser(@RequestBody User user) {
		User updateUser = userService.updateUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(updateUser, HttpStatus.OK);
		return responseEntity;
	}

}
