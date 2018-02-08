package com.ponto.inteligente.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.inteligente.api.converters.impl.CompanyConverter;
import com.ponto.inteligente.api.dto.CompanyDto;
import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.response.Response;
import com.ponto.inteligente.api.services.CompanyService;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {
	
	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyConverter companyConverter;
	
	/**
	 * 
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<CompanyDto>> findByCnpj(@PathVariable("cnpj") String cnpj) {
		Response<CompanyDto> response = new Response<CompanyDto>();
		Optional<Company> company = companyService.findByCnpj(cnpj);

		if (!company.isPresent()) {
			response.getErrors().add("Company not found for CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.companyConverter.convertCompanyToDto(company.get()));
		return ResponseEntity.ok(response);
	}	

}
