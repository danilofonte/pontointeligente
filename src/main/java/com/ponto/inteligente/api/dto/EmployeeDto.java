package com.ponto.inteligente.api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class EmployeeDto extends AbstractDto {

	private Long id;
	private String name;
	private String email;
	private Optional<String> password = Optional.empty();
	private Optional<String> hourValue = Optional.empty();
	private Optional<String> workingHoursQty = Optional.empty();
	private Optional<String> lunchHourQty = Optional.empty();

	public EmployeeDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Name cannot be empty.")
	@Length(min = 3, max = 200, message = "Name must be lenght of 3 between 200 characters.")
	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	@NotEmpty(message = "Email cannot be empty.")
	@Length(min = 3, max = 200, message = "Email must be lenght of 5 between 200 characters.")
	@Email(message="Email invalid.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getPassword() {
		return password;
	}

	public void setPassword(Optional<String> password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Optional<String> getHourValue() {
		return hourValue;
	}

	public void setHourValue(Optional<String> hourValue) {
		this.hourValue = hourValue;
	}

	public Optional<String> getWorkingHoursQty() {
		return workingHoursQty;
	}

	public void setWorkingHoursQty(Optional<String> workingHoursQty) {
		this.workingHoursQty = workingHoursQty;
	}

	public Optional<String> getLunchHourQty() {
		return lunchHourQty;
	}

	public void setLunchHourQty(Optional<String> lunchHourQty) {
		this.lunchHourQty = lunchHourQty;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", hourValue=" + hourValue + ", workingHoursQty=" + workingHoursQty + ", lunchHourQty=" + lunchHourQty
				+ "]";
	}
	
	

}
