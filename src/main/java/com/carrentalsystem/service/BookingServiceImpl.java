package com.carrentalsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.Car;
import com.carrentalsystem.entity.User;
import com.carrentalsystem.exception.BookingNotFoundException;
import com.carrentalsystem.exception.CarNotFoundException;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.CarRepository;
import com.carrentalsystem.repository.UserRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Booking saveBooking(Booking booking, int carId, int userId) {
		Optional<Car> optionalCar = carRepository.findById(carId);

		if (optionalCar.isEmpty()) {
			throw new CarNotFoundException("Car Not found with id: " + carId);
		}

		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalUser.isEmpty()) {
			throw new CarNotFoundException("User Not found with id: " + userId);
		}
		Car car = optionalCar.get();
		User user = optionalUser.get();
		booking.setCar(car);
		booking.setUser(user);

		Booking savedBooking = bookingRepository.save(booking);
		return savedBooking;
	}

	@Override
	public void deleteBooking(int bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new BookingNotFoundException("Booking Not found with id: " + bookingId);
		}
		Booking booking = optionalBooking.get();
		User user=booking.getUser();
		Set<Booking> bookings = user.getBooking();
		System.out.println("Bookings"+bookings.size());
		bookings.forEach(b->{
			if(b.getBookingId()==bookingId) {
				bookings.remove(b);
			}
		});
		System.out.println("Bookings"+bookings.size());

		//bookingRepository.delete(optionalBooking.get());
		userRepository.save(user);
	}
	
	@Override
	public List<Booking> getAllBooking() {
		List<Booking> allBooking = bookingRepository.findAll();
		return allBooking;
	}

	@Override
	public Booking getBookingById(int bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new BookingNotFoundException("Booking is not found with this ID: " + bookingId);
		}
		Booking booking = optionalBooking.get();
		return booking;
	}

}