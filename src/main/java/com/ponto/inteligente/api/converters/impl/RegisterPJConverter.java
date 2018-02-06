package com.ponto.inteligente.api.converters.impl;

import java.security.NoSuchAlgorithmException;

import org.springframework.validation.BindingResult;

import com.ponto.inteligente.api.dto.RegisterPJDto;
import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.enums.RoleEnum;
import com.ponto.inteligente.api.stereotypes.ConverterComponent;
import com.ponto.inteligente.api.utils.PasswordUtils;

@ConverterComponent
public class RegisterPJConverter {
	
	/**
	 * 
	 * 
	 * @param registerPJDto
	 * @return Company
	 */
	public Company convertRegisterPJDtoToCompany(RegisterPJDto registerPJDto) {
		Company company = new Company();
		company.setCnpj(registerPJDto.getCnpj());
		company.setCompanyName(registerPJDto.getCompanyName());

		return company;
	}

	/**
	 * 
	 * 
	 * @param registerPJDto
	 * @param result
	 * @return Employee
	 * @throws NoSuchAlgorithmException
	 */
	public Employee convertRegisterPJDtoToEmployee(RegisterPJDto registerPJDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Employee employee = new Employee();
		employee.setName(registerPJDto.getName());
		employee.setEmail(registerPJDto.getEmail());
		employee.setCpf(registerPJDto.getCpf());
		employee.setRole(RoleEnum.ROLE_ADMIN);
		employee.setPassword(PasswordUtils.generateCrypt(registerPJDto.getPassword()));

		return employee;
	}

	/**
	 * 
	 * 
	 * @param employee
	 * @return RegisterPJDto
	 */
	public RegisterPJDto convertEmployeeToRegisterPJDto(Employee employee) {
		RegisterPJDto registerPJDto = new RegisterPJDto();
		registerPJDto.setId(employee.getId());
		registerPJDto.setName(employee.getName());
		registerPJDto.setEmail(employee.getEmail());
		registerPJDto.setCpf(employee.getCpf());
		registerPJDto.setCompanyName(employee.getCompany().getCompanyName());
		registerPJDto.setCnpj(employee.getCompany().getCnpj());

		return registerPJDto;
	}

}
