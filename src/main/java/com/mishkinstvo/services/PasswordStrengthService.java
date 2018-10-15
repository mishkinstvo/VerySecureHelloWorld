package com.mishkinstvo.services;

public interface PasswordStrengthService {
	enum PasswordStrength {
		WEAK, ACCEPTABLE, STRONG
	};

	PasswordStrength rate(String password);
	boolean isWeak(String password);
}
