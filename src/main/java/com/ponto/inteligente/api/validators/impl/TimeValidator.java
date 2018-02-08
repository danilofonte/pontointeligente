package com.ponto.inteligente.api.validators.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ponto.inteligente.api.dto.AbstractDto;
import com.ponto.inteligente.api.dto.TimeDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.stereotypes.ValidatorComponent;
import com.ponto.inteligente.api.validators.Validator;

@ValidatorComponent
public class TimeValidator implements Validator {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * 
	 * @param abstractDto
	 * @param result
	 */
	public void validate(AbstractDto abstractDto, BindingResult result) {

		TimeDto timeDto = (TimeDto) abstractDto;

		if (timeDto.getEmployeeId() == null) {
			result.addError(new ObjectError("employee", "Employee not specified."));
			return;
		}

		Optional<Employee> employee = this.employeeService.findById(timeDto.getEmployeeId());
		if (!employee.isPresent()) {
			result.addError(new ObjectError("employee", "Employee not found by id."));
		}
	}

}
