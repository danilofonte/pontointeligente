package com.ponto.inteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.repositories.EmployeeRepository;
import com.ponto.inteligente.api.services.EmployeeService;

@Service
public class DefaultEmployeeService implements EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultEmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee persist(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findByCpf(String cpf) {		
		return Optional.ofNullable(this.employeeRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Employee> findByEmail(String email) {
		return Optional.ofNullable(this.employeeRepository.findByEmail(email));

	}

	@Override
	public Optional<Employee> findById(Long id) {
		return Optional.ofNullable(this.employeeRepository.findOne(id));
	}

}
