package com.mishkinstvo.entities;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NamedQuery(name = "userByLogin", query = "from User where login = :login")
public class User {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Column
	private String login;

	@Column
	private String password;

	public User() {}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
