package com.ponto.inteligente.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.repositories.CompanyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryTest {
	
	@Autowired
	CompanyRepository companyRepository;
	
	private static final String CNPJ = "000123123213123123";
	
	@Before
	public void setUp() {
		Company company = new Company();
		company.setCompanyName("test");
		company.setCnpj(CNPJ);
		this.companyRepository.save(company);
	}
	
	@After
	public final void tearDown() {
		this.companyRepository.deleteAll();
	}
	
	@Test
	public void testFindByCnpj() {
		Company company = this.companyRepository.findByCnpj(CNPJ);
		
		assertEquals(CNPJ, company.getCnpj());
	}

}
