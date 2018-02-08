package com.ponto.inteligente.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.inteligente.api.converters.impl.TimeConverter;
import com.ponto.inteligente.api.dto.TimeDto;
import com.ponto.inteligente.api.entities.Time;
import com.ponto.inteligente.api.response.Response;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.services.TimeService;
import com.ponto.inteligente.api.validators.impl.TimeValidator;

@RestController
@RequestMapping("/api/time")
@CrossOrigin(origins = "*")
public class TimeController {

	private static final Logger log = LoggerFactory.getLogger(TimeController.class);

	@Autowired
	private TimeService timeService;

	@Autowired
	private TimeValidator timeValidator;

	@Autowired
	private TimeConverter timeConverter;

	@Value("${pagination.qty_per_page}")
	private int qtyPerPage;

	/**
	 * 
	 * 
	 * @param employeeId
	 * @return ResponseEntity<Response<TimeDto>>
	 */
	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity<Response<Page<TimeDto>>> listTimeByEmployeeId(@PathVariable("employeeId") Long employeeId,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		Response<Page<TimeDto>> response = new Response<Page<TimeDto>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtyPerPage, Direction.valueOf(dir), ord);
		Page<Time> times = this.timeService.findByEmployeeId(employeeId, pageRequest);
		Page<TimeDto> timesDto = times.map(lancamento -> this.timeConverter.convertTimeToDto(lancamento));

		response.setData(timesDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return ResponseEntity<Response<TimeDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<TimeDto>> listById(@PathVariable("id") Long id) {
		Response<TimeDto> response = new Response<TimeDto>();
		Optional<Time> time = this.timeService.findById(id);

		if (!time.isPresent()) {
			response.getErrors().add("Time not found for id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.timeConverter.convertTimeToDto(time.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * 
	 * @param lancamento
	 * @param result
	 * @return ResponseEntity<Response<TimeDto>>
	 * @throws ParseException
	 * @throws java.text.ParseException 
	 */
	@PostMapping
	public ResponseEntity<Response<TimeDto>> add(@Valid @RequestBody TimeDto timeDto, BindingResult result)
			throws ParseException, java.text.ParseException {
		Response<TimeDto> response = new Response<TimeDto>();
		this.timeValidator.validate(timeDto, result);
		Time time = this.timeConverter.convertTimeDtoToTime(timeDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		time = this.timeService.persist(time);
		response.setData(this.timeConverter.convertTimeToDto(time));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * 
	 * @param id
	 * @param timeDto
	 * @return ResponseEntity<Response<Lancamento>>
	 * @throws ParseException
	 * @throws java.text.ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<TimeDto>> update(@PathVariable("id") Long id, @Valid @RequestBody TimeDto timeDto,
			BindingResult result) throws ParseException, java.text.ParseException {
		Response<TimeDto> response = new Response<TimeDto>();
		this.timeValidator.validate(timeDto, result);
		timeDto.setId(Optional.of(id));
		Time time = this.timeConverter.convertTimeDtoToTime(timeDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		time = this.timeService.persist(time);
		response.setData(this.timeConverter.convertTimeToDto(time));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Time>>
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> remove(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Optional<Time> time = this.timeService.findById(id);

		if (!time.isPresent()) {
			response.getErrors().add("Delete error. Time not found." + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.timeService.remove(id);
		return ResponseEntity.ok(new Response<String>());
	}

}
