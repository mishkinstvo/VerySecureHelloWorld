package com.mishkinstvo.services.impl;

import com.mishkinstvo.DbUserPrincipal;
import com.mishkinstvo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
@Primary
public class DefaultUserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return new DbUserPrincipal(userRepository.byLogin(username));
		}
		catch (NoResultException ex) {
			throw new UsernameNotFoundException(ex.getMessage());
		}
	}

	@Autowired
	public void setUserRepository(UserRepository userService) {
		this.userRepository = userService;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}
}
