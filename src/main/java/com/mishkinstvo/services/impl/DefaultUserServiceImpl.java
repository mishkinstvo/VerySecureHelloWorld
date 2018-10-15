package com.mishkinstvo.services.impl;

import com.mishkinstvo.entities.User;
import com.mishkinstvo.exceptions.DataValidationException;
import com.mishkinstvo.repositories.UserRepository;
import com.mishkinstvo.services.PasswordStrengthService;
import com.mishkinstvo.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class DefaultUserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private PasswordStrengthService passwordStrengthService;

	public User byLogin(String login) {
		return userRepository.byLogin(login);
	}

	public void persist(String login, String password) {
		if (passwordStrengthService.isWeak(password)) {
			throw new DataValidationException(WEAK_PASSWORD_ERROR_MESSAGE);
		}
		persist(new User(login, passwordEncoder.encode(password)));
	}

	private void persist(User user) {
		String login = user.getLogin();

		if (!StringUtils.isAlphanumeric(login)) {
			throw new DataValidationException(LOGIN_FORMAT_ERROR_MESSAGE);
		}
		if (StringUtils.length(login) < 2) {
			throw new DataValidationException(LOGIN_LENGTH_ERROR_MESSAGE);
		}
		try {
			byLogin(login);
			throw new DataValidationException(USER_EXISTS_ERROR_MESSAGE);
		}
		catch (NoResultException ex) {
			userRepository.persist(user);
		}
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void setPasswordStrengthService(PasswordStrengthService passwordStrengthService) {
		this.passwordStrengthService = passwordStrengthService;
	}

	public PasswordStrengthService getPasswordStrengthService() {
		return passwordStrengthService;
	}

	public static final String WEAK_PASSWORD_ERROR_MESSAGE = "Password is too weak";
	public static final String LOGIN_FORMAT_ERROR_MESSAGE = "Login has to contain only alphanumeric symbols";
	public static final String LOGIN_LENGTH_ERROR_MESSAGE = "Login has to contain at least two alphanumeric symbols";
	public static final String USER_EXISTS_ERROR_MESSAGE = "User with specified login already exists";
}
