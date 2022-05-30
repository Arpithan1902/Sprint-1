package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.entity.User;
import com.carrentalsystem.model.LoginRequest;
import com.carrentalsystem.model.LoginResponse;
import com.carrentalsystem.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

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

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginReq) {
		User loginUser = userService.doLogin(loginReq.getEmail(), loginReq.getPassword());
		LoginResponse loginResp = new LoginResponse();
		loginResp.setUserId(loginUser.getUserId());
		loginResp.setName(loginUser.getName());
		loginResp.setPhone(loginUser.getPhone());
		loginResp.setAddress(loginUser.getAddress());

		ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResp, HttpStatus.OK);
		return responseEntity;

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

	@GetMapping("user/login/{uname}/{passwd}")
	public ResponseEntity<User> singin(@PathVariable("email") String email, @PathVariable("passwd") String password) {
		User user = userService.doLogin(email, password);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}
}
