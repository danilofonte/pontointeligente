package com.ponto.inteligente.api.validators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ponto.inteligente.api.dto.AbstractDto;
import com.ponto.inteligente.api.dto.RegisterPJDto;
import com.ponto.inteligente.api.services.CompanyService;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.stereotypes.ValidatorComponent;
import com.ponto.inteligente.api.validators.Validator;

@ValidatorComponent
public class RegisterPJValidator implements Validator {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public void validate(AbstractDto dto, BindingResult result) {

		RegisterPJDto registerPJDto = (RegisterPJDto) dto;

		this.companyService.findByCnpj(registerPJDto.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("company", "Company already exist.")));

		this.employeeService.findByCpf(registerPJDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("employee", "CPF already used.")));

		this.employeeService.findByEmail(registerPJDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("employee", "Email already used.")));

	}

}
