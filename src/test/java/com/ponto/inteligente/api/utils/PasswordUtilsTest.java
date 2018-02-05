package com.ponto.inteligente.api.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	
	private static final String PASSWORD = "123456";
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	
	@Test
	public void testPasswordNotNull() {
		assertNotNull(PasswordUtils.generateCrypt(PASSWORD));
	}
	
	@Test 
	public void testPasswordHashGenerator() {
		String hash = PasswordUtils.generateCrypt(PASSWORD);
		
		assertTrue(bCryptPasswordEncoder.matches(PASSWORD, hash));
	}
}
