package com.carrentalsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.User;
import com.carrentalsystem.exception.AuthenticationFailedException;
import com.carrentalsystem.exception.UserNotFoundException;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookingRepository bookingRepository;
	
	

	@Override
	public User saveUser(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not existing with Id:" + userId);

		}
		User user = optionalUser.get();

		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}

	@Override
	public void deleteUSer(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not existing with Id:" + userId);

		}
		// User user=optionalUser.get();
		// Set<Booking> bookings=user.getBooking();
		// user.setBooking(null);
		// bookings.clear();
		// bookings.forEach(b->{bookingRepository.delete(b);

		// });
		userRepository.deleteById(userId);

	}

	@Override
	public User updateUser(User user) {
		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not existing with Id:" + user.getUserId());

		}

		User updatedUser = userRepository.save(user);

		return updatedUser;
	}

	@Override
	public void forgetPassword(String email, long phone, String newPassword) {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User is not exsiting with given email");

		}

		Optional<User> optionalUser2 = userRepository.findByPhone(phone);

		if (optionalUser2.isEmpty()) {
			throw new UserNotFoundException("User is not exsiting with given phone number");

		}
		User user = optionalUser.get();
		user.setPassword(newPassword);
		userRepository.save(user);

	}

	@Override
	public User doLogin(String email, String password) {
		User user = userRepository.login(email, password);
		if (user == null) {
			throw new AuthenticationFailedException("Username or password Invalid!!");
		}
		return user;
	}

	@Override
	public List<Booking> getAllBookingByUserId(int userId) {

		return bookingRepository.getAllBookingByUser(userId);
	}

}
