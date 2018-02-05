package com.ponto.inteligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ponto.inteligente.api.entities.Company;

@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<Company,Long> {
	
	
	Company findByCnpj(String cnpj);

}
