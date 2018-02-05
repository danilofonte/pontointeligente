package com.ponto.inteligente.api.services;

import java.util.Optional;

import com.ponto.inteligente.api.entities.Employee;

public interface EmployeeService {

	/**
	 * Persist employee in database
	 * 
	 * @param employee
	 * @return Employee
	 */
	Employee persist(Employee employee);
	
	/**
	 * Find employee by cpf
	 * 
	 * @param cpf
	 * @return Optional<Employee>
	 */
	Optional<Employee> findByCpf(String cpf);
	
	/**
	 * Find employee by email
	 * 
	 * @param email
	 * @return Optional<Employee>
	 */
	Optional<Employee> findByEmail(String email);
	
	/**
	 * Find employee by id
	 * 
	 * @param id
	 * @return Optional<Employee>
	 */
	Optional<Employee> findById(Long id);
	
}
