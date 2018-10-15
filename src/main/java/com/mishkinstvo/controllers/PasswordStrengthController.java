package com.mishkinstvo.controllers;

import com.mishkinstvo.services.PasswordStrengthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mishkinstvo.services.PasswordStrengthService.PasswordStrength;

@RestController
@RequestMapping("/passwords")
public class PasswordStrengthController {

	private PasswordStrengthService passwordStrengthService;

	@RequestMapping(path = "/strength", method = RequestMethod.GET)
	public PasswordStrength strength(@RequestParam String password) {
		return passwordStrengthService.rate(password);
	}

	@Autowired
	public void setPasswordStrengthService(PasswordStrengthService passwordStrengthService) {
		this.passwordStrengthService = passwordStrengthService;
	}

	public PasswordStrengthService getPasswordStrengthService() {
		return passwordStrengthService;
	}
}
