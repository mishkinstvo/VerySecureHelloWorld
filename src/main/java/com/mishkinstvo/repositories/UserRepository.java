package com.mishkinstvo.repositories;

import com.mishkinstvo.entities.User;

public interface UserRepository {
	User byLogin(String login);
	void persist(User user);
}
