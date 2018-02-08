package com.ponto.inteligente.api.converters.impl;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.ponto.inteligente.api.dto.RegisterPFDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.enums.RoleEnum;
import com.ponto.inteligente.api.stereotypes.ConverterComponent;
import com.ponto.inteligente.api.utils.PasswordUtils;

@ConverterComponent
public class RegisterPFConverter {
	
	/**
	 * 
	 * 
	 * @param registerPFDto
	 * @param result
	 * @return Employee
	 * @throws NoSuchAlgorithmException
	 */
	public Employee convertRegisterPFDtoToEmployee(RegisterPFDto registerPFDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Employee employee = new Employee();
		employee.setName(registerPFDto.getName());
		employee.setEmail(registerPFDto.getEmail());
		employee.setCpf(registerPFDto.getCpf());
		employee.setRole(RoleEnum.ROLE_USER);
		employee.setPassword(PasswordUtils.generateCrypt(registerPFDto.getPassword()));
		registerPFDto.getLunchHourQty()
				.ifPresent(lunchHourQty -> employee.setLunchHourQty(Float.valueOf(lunchHourQty)));
		registerPFDto.getWorkingHoursQty()
				.ifPresent(workingHourQty -> employee.setWorkingHoursQty(Float.valueOf(workingHourQty)));
		registerPFDto.getHourValue().ifPresent(hourValue -> employee.setHourValue(new BigDecimal(hourValue)));

		return employee;
	}

	/**
	 * 
	 * 
	 * @param employee
	 * @return RegisterPFDto
	 */
	public RegisterPFDto convertEmployeeToRegisterPFDto(Employee employee) {
		RegisterPFDto registerPFDto = new RegisterPFDto();
		registerPFDto.setId(employee.getId());
		registerPFDto.setName(employee.getName());
		registerPFDto.setEmail(employee.getEmail());
		registerPFDto.setCpf(employee.getCpf());
		registerPFDto.setCnpj(employee.getCompany().getCnpj());
		employee.getLunchHourQtyOpt().ifPresent(lunchHourQty -> registerPFDto
				.setLunchHourQty(Optional.of(Float.toString(lunchHourQty))));
		employee.getWorkingHoursQtyOpt().ifPresent(
				workingHoursQty -> registerPFDto.setWorkingHoursQty(Optional.of(Float.toString(workingHoursQty))));
		employee.getHourValueOpt()
				.ifPresent(hourValue -> registerPFDto.setHourValue(Optional.of(hourValue.toString())));

		return registerPFDto;
	}

}
