package com.ponto.inteligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ponto.inteligente.api.entities.Company;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.entities.Time;
import com.ponto.inteligente.api.enums.RoleEnum;
import com.ponto.inteligente.api.enums.TypeEnum;
import com.ponto.inteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TimeRepositoryTest {
	
	@Autowired
	private TimeRepository timeRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	private Long employeeId;
	
	private static final String CPF = "0001000000";
	private static final String EMAIL = "test@test.com";

	@Before
	public void setUp() throws Exception {
		Company company = this.companyRepository.save(initCompany());
		
		Employee employee = this.employeeRepository.save(initEmployee(company));
		this.employeeId = employee.getId();
		
		this.timeRepository.save(initTime(employee));
		this.timeRepository.save(initTime(employee));
	}

	@After
	public void tearDown() throws Exception {
		this.companyRepository.deleteAll();
	}

	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		List<Time> lancamentos = this.timeRepository.findByEmployeeId(employeeId);
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testFindTimeByEmployeeIdPaged() {
		PageRequest page = new PageRequest(0, 10);
		Page<Time> times = this.timeRepository.findByEmployeeId(employeeId, page);
		
		assertEquals(2, times.getTotalElements());
	}
	
	private Time initTime(Employee employee) {
		Time time = new Time();
		time.setDate(new Date());
		time.setType(TypeEnum.START_LUNCH_TIME);
		time.setEmployee(employee);
		return time;
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
