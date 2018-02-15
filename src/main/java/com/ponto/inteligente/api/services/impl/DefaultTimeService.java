package com.ponto.inteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ponto.inteligente.api.entities.Time;
import com.ponto.inteligente.api.repositories.TimeRepository;
import com.ponto.inteligente.api.services.TimeService;

@Service
public class DefaultTimeService implements TimeService  {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultEmployeeService.class);

	@Autowired
	private TimeRepository timeRepository;

	@Override
	public Page<Time> findByEmployeeId(Long employeeId, PageRequest pageRequest) {
		return this.timeRepository.findByEmployeeId(employeeId, pageRequest);
	}

	@Override
	@Cacheable("timeById")
	public Optional<Time> findById(Long id) {
		return Optional.ofNullable(this.timeRepository.findOne(id));
	}

	@Override
	@CachePut("timeById")
	public Time persist(Time time) {
		return this.timeRepository.save(time);
	}

	@Override
	public void remove(Long id) {
		this.timeRepository.delete(id);
	}

}
