package com.mishkinstvo.services.impl;

import com.mishkinstvo.services.PasswordStrengthService;
import org.springframework.stereotype.Service;

@Service
public class DefaultPasswordStrengthServiceImpl implements PasswordStrengthService {

	public PasswordStrength rate(String password) {
		if (password.length() > 7) {
			return PasswordStrength.STRONG;
		}
		if (password.length() > 5) {
			return PasswordStrength.ACCEPTABLE;
		}

		return PasswordStrength.WEAK;
	}

	public boolean isWeak(String password) {
		return PasswordStrength.WEAK.equals(rate(password));
	}
}
