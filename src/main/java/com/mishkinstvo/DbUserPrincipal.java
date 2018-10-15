package com.mishkinstvo;

import com.mishkinstvo.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class DbUserPrincipal implements UserDetails {

	private User user;

	public DbUserPrincipal(User user) {
		this.user = user;
	}

	/*
	 * As no requirements for authorisation were provided,
	 * it was assumed that a single in-memory authority should be enough
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getLogin();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
