package com.carrentalsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.entity.User;
import com.carrentalsystem.exception.AuthenticationFailedException;
import com.carrentalsystem.exception.UserNotFoundException;
import com.carrentalsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserById(int userId) {
		Optional<User> optionalUser=userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not existing with Id:"+userId);
			
		}
		User user=optionalUser.get();
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers=userRepository.findAll();
		return allUsers;
	}

	@Override
	public void deleteUSer(int userId) {
		Optional<User> optionalUser=userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not existing with Id:"+userId);
			
		}
		userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(User user) {
		Optional<User> optionalUser=userRepository.findById(user.getUserId());
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not existing with Id:"+user.getUserId());
			
		}
		User updateUser=optionalUser.get();
		
		return updateUser;
	}
	
	
	@Override
	public User doLogin(String email,String password) {
		User user=userRepository.login(email,password);
		if(user==null) {
			throw new AuthenticationFailedException("Username or password Invalid!!");
		}
		return user;
	}
	
	

}
