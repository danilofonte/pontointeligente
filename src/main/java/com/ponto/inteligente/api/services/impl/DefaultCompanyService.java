package com.ponto.inteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.repositories.CompanyRepository;
import com.ponto.inteligente.api.services.CompanyService;

@Service
public class DefaultCompanyService implements CompanyService {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultCompanyService.class);
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Optional<Company> findByCnpj(String cnpj) {
		return Optional.ofNullable(companyRepository.findByCnpj(cnpj));
	}

	@Override
	public Company persist(Company company) {
		return this.companyRepository.save(company);
	}

}
