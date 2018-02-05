package com.ponto.inteligente.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ponto.inteligente.api.entities.Time;

@Transactional(readOnly = true)
@NamedQueries({@NamedQuery(name="TimeRepository.findByEmployeeId",query=" SELECT T from Time T where T.employee.id =:employeeId")})
public interface TimeRepository extends JpaRepository<Time,Long> {
	
	List<Time> findByEmployeeId(@Param("employeeId") Long id);
	
	Page<Time> findByEmployeeId(@Param("employeeId") Long id, Pageable pageable);

}
