package com.ponto.inteligente.api.converters.impl;

import java.util.Optional;

import com.ponto.inteligente.api.dto.EmployeeDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.stereotypes.ConverterComponent;

@ConverterComponent
public class EmployeeConverter {

	/**
	 * 
	 * 
	 * @param employee
	 * @return EmployeeDto
	 */
	public EmployeeDto convertToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setName(employee.getName());
		employee.getLunchHourQtyOpt().ifPresent(lunchHourQty -> employeeDto.setLunchHourQty(Optional.of(Float.toString(lunchHourQty))));
		employee.getWorkingHoursQtyOpt().ifPresent(workingHoursQty -> employeeDto.setWorkingHoursQty(Optional.of(Float.toString(workingHoursQty))));
		employee.getHourValueOpt().ifPresent(hourValue -> employeeDto.setHourValue(Optional.of(hourValue.toString())));

		return employeeDto;
	}

}
