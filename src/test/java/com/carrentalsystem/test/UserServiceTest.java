package com.carrentalsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.carrentalsystem.entity.User;
import com.carrentalsystem.repository.UserRepository;
import com.carrentalsystem.service.UserService;
import com.carrentalsystem.service.UserServiceImpl;

@SpringBootTest
class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserRepository userRepository;

	@Test
	void testGetUserById() {
		User user = new User();

		user.setUserId(100);
		user.setName("Venu");
		user.setAddress("Mysuru");
		user.setPhone(900909);
		user.setEmail("venu@gmail.com");
		user.setPassword("venu123");

		Optional<User> optionalUser = Optional.of(user);

		when(userRepository.findById(100)).thenReturn(optionalUser);
		User myUser = userService.getUserById(100);
		assertEquals("Venu", myUser.getName());

	}

	@Test
	void testDeleteUser() {
		User user = new User();

		user.setUserId(100);
		user.setName("Venu");
		user.setAddress("Mysuru");
		user.setPhone(900909);
		user.setEmail("venu@gmail.com");
		user.setPassword("venu123");

		Optional<User> optionalUser = Optional.of(user);

		when(userRepository.findById(100)).thenReturn(optionalUser);
		
		userService.deleteUSer(100);

	}

	@Test
	void testSaveUser() {

		User user = new User();
		user.setUserId(100);
		user.setName("Venu");
		user.setAddress("Mysuru");
		user.setPhone(900909);
		user.setEmail("venu@gmail.com");
		user.setPassword("venu123");

		when(userRepository.save(user)).thenReturn(user);

		User newUser = userService.saveUser(user);

		assertEquals("Venu", newUser.getName());

	}

	@Test
	void testUpdateUser() {

		User user = new User();
		user.setUserId(100);
		user.setName("Veena");
		user.setAddress("Mysuru");
		user.setPhone(900909);
		user.setEmail("veena@gmail.com");
		user.setPassword("veena123");

		Optional<User> optionalUser = Optional.of(user);

		when(userRepository.findById(100)).thenReturn(optionalUser);
		when(userRepository.save(user)).thenReturn(user);

		User newUser2 = userService.updateUser(user);

		assertEquals("Veena", newUser2.getName());
	}

	@Test
	void testGetAllUser() {

		User user = new User();
		user.setUserId(100);
		user.setName("Veena");
		user.setAddress("Mysuru");
		user.setPhone(900909);
		user.setEmail("veena@gmail.com");
		user.setPassword("veena123");

		User user1 = new User();
		user1.setUserId(101);
		user1.setName("Vani");
		user1.setAddress("Bangalore");
		user1.setPhone(900909);
		user1.setEmail("vani@gmail.com");
		user1.setPassword("vani123");

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user);

		when(userRepository.findAll()).thenReturn(userList);
		List<User> users = userService.getAllUsers();
		assertEquals(userList, users);
		assertEquals(2, users.size());
	}

}
