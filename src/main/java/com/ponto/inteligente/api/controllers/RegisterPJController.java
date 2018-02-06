package com.ponto.inteligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.inteligente.api.converters.impl.RegisterPJConverter;
import com.ponto.inteligente.api.dto.RegisterPJDto;
import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.response.Response;
import com.ponto.inteligente.api.services.CompanyService;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.validators.impl.RegisterPJValidator;

@RestController
@RequestMapping("/api/register-pj")
@CrossOrigin(origins = "*")
public class RegisterPJController {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterPJController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private RegisterPJValidator registerPJValidator;
	
	@Autowired
	private RegisterPJConverter registerPJConverter;
	
	@PostMapping
	public ResponseEntity<Response<RegisterPJDto>> register(@Valid @RequestBody RegisterPJDto registerPJDto,
			BindingResult result) throws NoSuchAlgorithmException {
		Response<RegisterPJDto> response = new Response<RegisterPJDto>();

		this.registerPJValidator.validate(registerPJDto, result);
		Company company = this.registerPJConverter.convertRegisterPJDtoToCompany(registerPJDto);
		Employee employee = this.registerPJConverter.convertRegisterPJDtoToEmployee(registerPJDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.companyService.persist(company);
		employee.setCompany(company);
		this.employeeService.persist(employee);

		response.setData(this.registerPJConverter.convertEmployeeToRegisterPJDto(employee));
		return ResponseEntity.ok(response);
	}


}
