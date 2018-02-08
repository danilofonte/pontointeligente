package com.ponto.inteligente.api.converters.impl;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ponto.inteligente.api.dto.TimeDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.entities.Time;
import com.ponto.inteligente.api.enums.TypeEnum;
import com.ponto.inteligente.api.services.TimeService;
import com.ponto.inteligente.api.stereotypes.ConverterComponent;
import org.apache.commons.lang3.EnumUtils;


@ConverterComponent
public class TimeConverter {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private TimeService timeService;


	/**
	 * 
	 * 
	 * @param time
	 * @return LancamentoDto
	 */
	public TimeDto convertTimeToDto(Time time) {
		TimeDto timeDto = new TimeDto();
		timeDto.setId(Optional.of(time.getId()));
		timeDto.setDate(this.dateFormat.format(time.getDate()));
		timeDto.setType(time.getType().toString());
		timeDto.setDescription(time.getDescription());
		timeDto.setLocation(time.getLocation());
		timeDto.setEmployeeId(time.getEmployee().getId());

		return timeDto;
	}

	/**
	 * 
	 * 
	 * @param timeDto
	 * @param result
	 * @return Time
	 * @throws ParseException 
	 * @throws java.text.ParseException 
	 */
	public Time convertTimeDtoToTime(TimeDto timeDto, BindingResult result) throws ParseException, java.text.ParseException {
		Time time = new Time();

		if (timeDto.getId().isPresent()) {
			Optional<Time> lanc = this.timeService.findById(timeDto.getId().get());
			if (lanc.isPresent()) {
				time = lanc.get();
			} else {
				result.addError(new ObjectError("time", "Time not found."));
			}
		} else {
			time.setEmployee(new Employee());
			time.getEmployee().setId(timeDto.getEmployeeId());
		}

		time.setDescription(timeDto.getDescription());
		time.setLocation(timeDto.getLocation());
		time.setDate(this.dateFormat.parse(timeDto.getDate()));

		if (EnumUtils.isValidEnum(TypeEnum.class, timeDto.getType())) {
			time.setType(TypeEnum.valueOf(timeDto.getType()));
		} else {
			result.addError(new ObjectError("type", "Invalid type."));
		}

		return time;
	}
	
}
