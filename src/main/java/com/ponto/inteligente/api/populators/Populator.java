package com.ponto.inteligente.api.populators;

import org.springframework.validation.BindingResult;

import com.ponto.inteligente.api.dto.AbstractDto;

public interface Populator {
	
	void populate(Object object, AbstractDto abstractDto, BindingResult result) throws Exception;

}
