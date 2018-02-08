package com.ponto.inteligente.api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class RegisterPFDto extends AbstractDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String cpf;
	private Optional<String> hourValue = Optional.empty();
	private Optional<String> workingHoursQty = Optional.empty();
	private Optional<String> lunchHourQty = Optional.empty();
	private String cnpj;

	public RegisterPFDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Name cannot be empty.")
	@Length(min = 3, max = 200, message = "Name must be lenght of 3 between 200 characters.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "Email cannot be empty.")
	@Length(min = 5, max = 200, message = "Email must be lenght of 5 between 200 characters.")
	@Email(message="Email invalid.")
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

	public void setSenha(String password) {
		this.password = password;
	}

	@NotEmpty(message = "CPF cannot be empty.")
	@CPF(message="CPF invalid")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	@NotEmpty(message = "CNPJ cannot be empty.")
	@CNPJ(message="CNPJ invalid.")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "RegisterPFDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", cpf="
				+ cpf + ", hourValue=" + hourValue + ", workingHoursQty=" + workingHoursQty + ", lunchHourQty="
				+ lunchHourQty + ", cnpj=" + cnpj + "]";
	}
	
	
}
