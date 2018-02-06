package com.ponto.inteligente.api.validators;

import org.springframework.validation.BindingResult;

import com.ponto.inteligente.api.dto.AbstractDto;


public interface Validator {

	void validate(AbstractDto dto, BindingResult result);
	
}