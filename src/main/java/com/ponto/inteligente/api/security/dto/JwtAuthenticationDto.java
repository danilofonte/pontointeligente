package com.ponto.inteligente.api.security.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class JwtAuthenticationDto {
	
	private String email;
	private String password;

	public JwtAuthenticationDto() {
	}

	@NotEmpty(message = "Email cannot be null.")
	@Email(message = "Invalid email.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Password cannot be empty.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationDto [email=" + email + ", password=" + password + "]";
	}

	

}
