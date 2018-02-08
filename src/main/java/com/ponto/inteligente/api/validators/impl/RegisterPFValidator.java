package com.ponto.inteligente.api.validators.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ponto.inteligente.api.dto.AbstractDto;
import com.ponto.inteligente.api.dto.RegisterPFDto;
import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.services.CompanyService;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.stereotypes.ValidatorComponent;
import com.ponto.inteligente.api.validators.Validator;

@ValidatorComponent
public class RegisterPFValidator implements Validator {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 
	 * 
	 * @param registerPFDto
	 * @param result
	 */
	public void validate(AbstractDto dto, BindingResult result) {
		
		RegisterPFDto registerPFDto = (RegisterPFDto) dto;
		
		Optional<Company> company = this.companyService.findByCnpj(registerPFDto.getCnpj());
		if (!company.isPresent()) {
			result.addError(new ObjectError("company", "Company not found."));
		}
		
		this.employeeService.findByCpf(registerPFDto.getCpf())
			.ifPresent(func -> result.addError(new ObjectError("employee", "CPF already exist.")));

		this.employeeService.findByEmail(registerPFDto.getEmail())
			.ifPresent(func -> result.addError(new ObjectError("employee", "Email already exist.")));
	}


}
