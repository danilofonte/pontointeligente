package com.ponto.inteligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.enums.RoleEnum;
import com.ponto.inteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	private static final String CPF = "0001000000";
	private static final String EMAIL = "test@test.com";
	
	@Before
	public void setUp() throws Exception {
		Company company = this.companyRepository.save(initCompany());
		
		this.employeeRepository.save(initEmployee(company));
	}
	
	@After
	public final void tearDown() {
		this.companyRepository.deleteAll();
	}
	
	@Test
	public void testFindEmployeeByEmail() {
		Employee employee = this.employeeRepository.findByEmail(EMAIL);

		assertEquals(EMAIL, employee.getEmail());
	}

	@Test
	public void testFindEmployeeByCpf() {
		Employee employee = this.employeeRepository.findByCpf(CPF);

		assertEquals(CPF, employee.getCpf());
	}

	@Test
	public void testFindEmployeeByCpfOrEmail() {
		Employee employee = this.employeeRepository.findByCpfOrEmail(CPF, EMAIL);

		assertNotNull(employee);
	}

	@Test
	public void testFindEmployeeByCpfOrEmailByInvalidEmail() {
		Employee employee = this.employeeRepository.findByCpfOrEmail(CPF, "email@invalido.com");

		assertNotNull(employee);
	}

	@Test
	public void testFindEmployeeByCpfOrEmailByInvalidCpf() {
		Employee employee = this.employeeRepository.findByCpfOrEmail("12345678901", EMAIL);

		assertNotNull(employee);
	}
	
	private Employee initEmployee(Company company) throws NoSuchAlgorithmException {
		Employee employee = new Employee();
		employee.setName("Fulano de Tal");
		employee.setRole(RoleEnum.ROLE_USER);
		employee.setPassword(PasswordUtils.generateCrypt("123456"));
		employee.setCpf(CPF);
		employee.setEmail(EMAIL);
		employee.setCompany(company);
		return employee;
	}
	
	private Company  initCompany() {
		Company company = new Company();
		company.setCompanyName("Empresa de exemplo");
		company.setCnpj("51463645000100");
		return company;
	}
	
}
