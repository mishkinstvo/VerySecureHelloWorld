package com.mishkinstvo.repositories.impl;

import com.mishkinstvo.entities.User;
import com.mishkinstvo.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public User byLogin(String login) {
		TypedQuery<User> query = entityManager.createNamedQuery(USER_BY_LOGIN_QUERY_NAME, User.class);
		query.setParameter(LOGIN_PARAMETER_NAME, login);
		return query.getSingleResult();
	}

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
	}

	public static final String USER_BY_LOGIN_QUERY_NAME = "userByLogin";
	public static final String LOGIN_PARAMETER_NAME = "login";
}
