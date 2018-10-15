package com.mishkinstvo.services;

import com.mishkinstvo.entities.User;

public interface UserService {
	User byLogin(String login);
	void persist(String login, String password);
}
