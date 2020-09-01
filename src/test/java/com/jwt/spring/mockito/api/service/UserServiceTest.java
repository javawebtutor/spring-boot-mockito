package com.jwt.spring.mockito.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.jwt.spring.mockito.api.dao.UserRepository;
import com.jwt.spring.mockito.api.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	public void testAddUser_adds_user() {
		User user = createUser();
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.addUser(user);
		assertNotNull(result);
		verify(userRepository).save(user);
	}

	@Test
	public void testAddUser_should_not_adds_user() {
		User user = createUser();
		when(userRepository.save(user)).thenReturn(null);
		User result = userService.addUser(user);
		assertNull(result);
		verify(userRepository).save(user);
	}

	@Test
	public void testGetUsers_should_return_user() {
		List<User> users = createUserList();
		when(userRepository.findAll()).thenReturn(users);
		List<User> result = userService.getUsers();
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(userRepository).findAll();

	}

	@Test
	public void testGetUsers_should_not_return_user() {
		when(userRepository.findAll()).thenReturn(null);
		List<User> result = userService.getUsers();
		assertNull(result);
		verify(userRepository).findAll();

	}

	@Test
	public void testGetUserbyAddress() {
		List<User> users = createUserList();
		when(userRepository.findByAddress("test")).thenReturn(users);
		List<User> result = userService.getUserbyAddress("test");
		assertNotNull(result);
		verify(userRepository).findByAddress("test");
	}

	@Test
	public void testGetUserbyAddress_should_not_return_address() {
		when(userRepository.findByAddress("abc")).thenReturn(null);
		List<User> result = userService.getUserbyAddress("abc");
		assertNull(result);
		verify(userRepository).findByAddress("abc");

	}

	@Test
	public void testDeleteUser_should_delete_user() {
		User user = createUser();
		userService.deleteUser(user);
		verify(userRepository).delete(user);
	}

	private User createUser() {
		User user = new User();
		user.setId(1);
		user.setName("Mukesh");
		user.setAge(34);
		user.setAddress("abc");
		return user;
	}

	private List<User> createUserList() {
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setId(1);
		user.setName("Ravi");
		user.setAge(28);
		user.setAddress("test");
		users.add(user);
		return users;
	}

}
