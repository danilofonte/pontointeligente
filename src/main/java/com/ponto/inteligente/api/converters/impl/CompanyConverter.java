package com.ponto.inteligente.api.converters.impl;

import com.ponto.inteligente.api.dto.CompanyDto;
import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.stereotypes.ConverterComponent;

@ConverterComponent
public class CompanyConverter {

	/**
	 * 
	 * 
	 * @param company
	 * @return CompanyDto
	 */
	public CompanyDto convertCompanyToDto(Company company) {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(company.getId());
		companyDto.setCnpj(company.getCnpj());
		companyDto.setCompanyName(company.getCompanyName());

		return companyDto;
	}
	
}
