package com.ponto.inteligente.api.services;

import java.util.Optional;

import com.ponto.inteligente.api.entities.Company;

public interface CompanyService {
	
	/**
	 * Find company by cnpj
	 * 
	 * @param cnpj
	 * @return Optional<Company>
	 */
	Optional<Company> findByCnpj(final String cnpj);
	
	/**
	 * Save company
	 * 
	 * @param company
	 * @return Company
	 */
	Company persist(Company company);

}
