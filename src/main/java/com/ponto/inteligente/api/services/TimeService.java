package com.ponto.inteligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ponto.inteligente.api.entities.Time;

public interface TimeService {
	
	/**
	 * Return list of time by employee id.
	 * 
	 * @param employeeId
	 * @param pageRequest
	 * @return Page<Time>
	 */
	Page<Time> findByEmployeeId(Long employeeId, PageRequest pageRequest);
	
	/**
	 * Return time by Id.
	 * 
	 * @param id
	 * @return Optional<Time>
	 */
	Optional<Time> findById(Long id);
	
	/**
	 * Save time in database.
	 * 
	 * @param time
	 * @return Time
	 */
	Time persist(Time time);
	
	/**
	 * Remove time from database.
	 * 
	 * @param id
	 */
	void remove(Long id);

}
