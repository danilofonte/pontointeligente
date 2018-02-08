package com.ponto.inteligente.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.inteligente.api.converters.impl.EmployeeConverter;
import com.ponto.inteligente.api.dto.EmployeeDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.populators.impl.EmployeePopulator;
import com.ponto.inteligente.api.response.Response;
import com.ponto.inteligente.api.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeePopulator employeePopulator;
	
	@Autowired
	private EmployeeConverter employeeConverter;
	
	/**
	 * 
	 * 
	 * @param id
	 * @param employeeDto
	 * @param result
	 * @return ResponseEntity<Response<FuncionarioDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EmployeeDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) throws NoSuchAlgorithmException {
		Response<EmployeeDto> response = new Response<EmployeeDto>();

		Optional<Employee> employee = this.employeeService.findById(id);
		if (!employee.isPresent()) {
			result.addError(new ObjectError("funcionario", "Employee not found."));
		}

		this.employeePopulator.populate(employee.get(), employeeDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.employeeService.persist(employee.get());
		response.setData(this.employeeConverter.convertToEmployeeDto(employee.get()));

		return ResponseEntity.ok(response);
	}

	

	


}
