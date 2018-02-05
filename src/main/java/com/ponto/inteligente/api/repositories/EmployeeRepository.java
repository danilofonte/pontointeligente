package com.ponto.inteligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ponto.inteligente.api.entities.Employee;

@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
	Employee findByCpf(String cpg);
	
	Employee findByEmail(String email);
	
	Employee findByCpfOrEmail(String cpf, String email);

}
