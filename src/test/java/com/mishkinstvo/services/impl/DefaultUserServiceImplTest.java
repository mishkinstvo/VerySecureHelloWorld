package com.mishkinstvo.services.impl;

import com.mishkinstvo.entities.User;
import com.mishkinstvo.exceptions.DataValidationException;
import com.mishkinstvo.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.NoResultException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class DefaultUserServiceImplTest {

	@InjectMocks
	private DefaultUserServiceImpl userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder passwordEncoder;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		userService.setPasswordStrengthService(new DefaultPasswordStrengthServiceImpl());
		when(userRepository.byLogin(EXISTING_LOGIN)).thenReturn(new User(EXISTING_LOGIN, STRONG_PASSWORD));
		when(userRepository.byLogin(NON_EXISTING_LOGIN)).thenThrow(NoResultException.class);
	}

	@Test
	public void byLogin() {
		User user = userService.byLogin(EXISTING_LOGIN);
		assertEquals(user.getLogin(), EXISTING_LOGIN);

		try {
			userService.byLogin(NON_EXISTING_LOGIN);
			fail("For non existing login NoResultException should be thrown");
		}
		catch(NoResultException ex) {}
	}

	@Test
	public void persist() {
		try {
			userService.persist(NON_EXISTING_LOGIN, EMPTY_PASSWORD);
			fail("For empty password DataValidationException should be thrown");
		}
		catch (DataValidationException ex) {
			assertEquals(DefaultUserServiceImpl.WEAK_PASSWORD_ERROR_MESSAGE, ex.getMessage());
		}

		try {
			userService.persist(NON_EXISTING_LOGIN, WEAK_PASSWORD);
			fail("For weak password DataValidationException should be thrown");
		}
		catch (DataValidationException ex) {
			assertEquals(DefaultUserServiceImpl.WEAK_PASSWORD_ERROR_MESSAGE, ex.getMessage());
		}

		try {
			userService.persist(WRONG_FORMAT_LOGIN, STRONG_PASSWORD);
			fail("For wrong formatted login password DataValidationException should be thrown");
		}
		catch (DataValidationException ex) {
			assertEquals(DefaultUserServiceImpl.LOGIN_FORMAT_ERROR_MESSAGE, ex.getMessage());
		}

		try {
			userService.persist(EXISTING_LOGIN, STRONG_PASSWORD);
			fail("When trying to persist a user with existing login password DataValidationException should be thrown");
		}
		catch (DataValidationException ex) {
			assertEquals(DefaultUserServiceImpl.USER_EXISTS_ERROR_MESSAGE, ex.getMessage());
		}

		userService.persist(NON_EXISTING_LOGIN, STRONG_PASSWORD);
		userService.persist(NON_EXISTING_LOGIN, ACCEPTABLE_PASSWORD);
	}

	public final static String EXISTING_LOGIN = "existingLogin";
	public final static String NON_EXISTING_LOGIN = "nonExistingLogin";
	public final static String WRONG_FORMAT_LOGIN = "a%b1";
	public final static String EMPTY_PASSWORD = "";
	public final static String WEAK_PASSWORD = "a";
	public final static String ACCEPTABLE_PASSWORD = "123456";
	public final static String STRONG_PASSWORD = "a12345678";
}