package com.ponto.inteligente.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

import com.ponto.inteligente.api.converters.impl.RegisterPFConverter;
import com.ponto.inteligente.api.dto.RegisterPFDto;
import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.response.Response;
import com.ponto.inteligente.api.services.CompanyService;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.validators.impl.RegisterPFValidator;

@RestController
@RequestMapping("/api/register-pf")
@CrossOrigin(origins = "*")
public class RegisterPFController {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterPFController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RegisterPFConverter registerPFConverter;
	
	@Autowired
	private RegisterPFValidator registerPFValidator;
	
	/**
	 * 
	 * 
	 * @param registerPFDto
	 * @param result
	 * @return ResponseEntity<Response<RegisterPFDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<RegisterPFDto>> register(@Valid @RequestBody RegisterPFDto registerPFDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando PF: {}", registerPFDto.toString());
		Response<RegisterPFDto> response = new Response<RegisterPFDto>();

		this.registerPFValidator.validate(registerPFDto, result);
		Employee employee = this.registerPFConverter.convertRegisterPFDtoToEmployee(registerPFDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PF: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Company> company = this.companyService.findByCnpj(registerPFDto.getCnpj());
		company.ifPresent(emp -> employee.setCompany(emp));
		this.employeeService.persist(employee);

		response.setData(this.registerPFConverter.convertEmployeeToRegisterPFDto(employee));
		return ResponseEntity.ok(response);
	}


}
