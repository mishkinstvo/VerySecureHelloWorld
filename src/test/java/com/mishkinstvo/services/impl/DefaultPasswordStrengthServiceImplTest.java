package com.mishkinstvo.services.impl;

import org.junit.Before;
import org.junit.Test;

import static com.mishkinstvo.services.PasswordStrengthService.PasswordStrength;
import static org.junit.Assert.*;

public class DefaultPasswordStrengthServiceImplTest {

	private DefaultPasswordStrengthServiceImpl passwordStrengthService;

	@Before
	public void setup() {
		passwordStrengthService = new DefaultPasswordStrengthServiceImpl();
	}

	@Test
	public void rate() {
		assertEquals(passwordStrengthService.rate(EMPTY_PASSWORD), PasswordStrength.WEAK);
		assertEquals(passwordStrengthService.rate(WEAK_PASSWORD), PasswordStrength.WEAK);
		assertEquals(passwordStrengthService.rate(ACCEPTABLE_PASSWORD), PasswordStrength.ACCEPTABLE);
		assertEquals(passwordStrengthService.rate(STRONG_PASSWORD), PasswordStrength.STRONG);
	}

	@Test
	public void isWeak() {
		assertTrue(passwordStrengthService.isWeak(EMPTY_PASSWORD));
		assertTrue(passwordStrengthService.isWeak(WEAK_PASSWORD));
		assertFalse(passwordStrengthService.isWeak(ACCEPTABLE_PASSWORD));
		assertFalse(passwordStrengthService.isWeak(STRONG_PASSWORD));
	}

	public static String EMPTY_PASSWORD = "";
	public static String WEAK_PASSWORD = "abc";
	public static String ACCEPTABLE_PASSWORD = "123456";
	public static String STRONG_PASSWORD = "A2345678";
}