package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User getUserById(int userId);
	
	public List<User> getAllUsers();
	
	public void deleteUSer(int userId);
	
	public User updateUser(User user);
	
	public User doLogin(String email, String password);
	
	public List<Booking> getAllBookingByUserId(int userId);
	
	public void forgetPassword(String email,long phone,String newPassword);
	
	

}
