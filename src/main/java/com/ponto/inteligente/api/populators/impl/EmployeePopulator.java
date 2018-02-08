package com.ponto.inteligente.api.populators.impl;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ponto.inteligente.api.dto.AbstractDto;
import com.ponto.inteligente.api.dto.EmployeeDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.populators.Populator;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.stereotypes.PopulatorComponent;
import com.ponto.inteligente.api.utils.PasswordUtils;

@PopulatorComponent
public class EmployeePopulator implements Populator {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * 
	 * @param employee
	 * @param employeeDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	public void populate(Object object, AbstractDto abstractDto, BindingResult result) throws NoSuchAlgorithmException {
		Employee employee = (Employee) object;
		EmployeeDto employeeDto = (EmployeeDto) abstractDto;

		employee.setName(employeeDto.getName());

		if (!employee.getEmail().equals(employeeDto.getEmail())) {
			this.employeeService.findByEmail(employeeDto.getEmail()).ifPresent(func -> result.addError(new ObjectError("email", "Email already exist.")));
			employee.setEmail(employeeDto.getEmail());
		}

		employee.setLunchHourQty(null);
		employeeDto.getLunchHourQty().ifPresent(lunchHourQty -> employee.setLunchHourQty(Float.valueOf(lunchHourQty)));

		employee.setWorkingHoursQty(null);
		employeeDto.getWorkingHoursQty().ifPresent(workingHoursQty -> employee.setWorkingHoursQty(Float.valueOf(workingHoursQty)));

		employee.setHourValue(null);
		employeeDto.getHourValue().ifPresent(hourValue -> employee.setHourValue(new BigDecimal(hourValue)));

		if (employeeDto.getPassword().isPresent()) {
			employee.setPassword(PasswordUtils.generateCrypt(employeeDto.getPassword().get()));
		}
	}

}
