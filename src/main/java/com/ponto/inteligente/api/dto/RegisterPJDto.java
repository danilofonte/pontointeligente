package com.ponto.inteligente.api.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class RegisterPJDto {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String cpf;
	private String companyName;
	private String cnpj;

	public RegisterPJDto() {
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
	@Email(message="Email inválido.")
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

	@NotEmpty(message = "CPF cannot be empty.")
	@CPF(message="CPF invalid")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotEmpty(message = "Company name cannot be empty.")
	@Length(min = 5, max = 200, message = "Company name must be lenght of 5 between 200 characters.")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		return "RegisterPJDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", cpf="
				+ cpf + ", companyName=" + companyName + ", cnpj=" + cnpj + "]";
	}
	
}
