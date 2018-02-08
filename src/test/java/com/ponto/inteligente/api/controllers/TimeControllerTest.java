package com.ponto.inteligente.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ponto.inteligente.api.dto.TimeDto;
import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.entities.Time;
import com.ponto.inteligente.api.enums.TypeEnum;
import com.ponto.inteligente.api.services.EmployeeService;
import com.ponto.inteligente.api.services.TimeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TimeControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TimeService timeService;
	
	@MockBean
	private EmployeeService employeeService;
	
	private static final String URL_BASE = "/api/lancamentos/";
	private static final Long EMPLOYEE_ID = 1L;
	private static final Long TIME_ID = 1L;
	private static final String TYPE = TypeEnum.START_WORK_TIME.name();
	private static final Date DATE = new Date();
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	@WithMockUser
	public void testCadastrarLancamento() throws Exception {
		Time time = initTime();
		BDDMockito.given(this.employeeService.findById(Mockito.anyLong())).willReturn(Optional.of(new Employee()));
		BDDMockito.given(this.timeService.persist(Mockito.any(Time.class))).willReturn(time);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.getJsonPostRequest())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(TIME_ID))
				.andExpect(jsonPath("$.data.tipo").value(TYPE))
				.andExpect(jsonPath("$.data.data").value(this.dateFormat.format(DATE)))
				.andExpect(jsonPath("$.data.funcionarioId").value(EMPLOYEE_ID))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testCadastrarLancamentoFuncionarioIdInvalido() throws Exception {
		BDDMockito.given(this.employeeService.findById(Mockito.anyLong())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.getJsonPostRequest())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Employee not found by id."))
				.andExpect(jsonPath("$.data").isEmpty());
	}
	
	@Test
	@WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
	public void testRemoverLancamento() throws Exception {
		BDDMockito.given(this.timeService.findById(Mockito.anyLong())).willReturn(Optional.of(new Time()));

		mvc.perform(MockMvcRequestBuilders.delete(URL_BASE + TIME_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void testRemoverLancamentoAcessoNegado() throws Exception {
		BDDMockito.given(this.timeService.findById(Mockito.anyLong())).willReturn(Optional.of(new Time()));

		mvc.perform(MockMvcRequestBuilders.delete(URL_BASE + TIME_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	private String getJsonPostRequest() throws JsonProcessingException {
		TimeDto timeDto = new TimeDto();
		timeDto.setId(null);
		timeDto.setDate(this.dateFormat.format(DATE));
		timeDto.setType(TYPE);
		timeDto.setEmployeeId(EMPLOYEE_ID);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(timeDto);
	}

	private Time initTime() {
		Time time = new Time();
		time.setId(TIME_ID);
		time.setDate(DATE);
		time.setType(TypeEnum.valueOf(TYPE));
		time.setEmployee(new Employee());
		time.getEmployee().setId(EMPLOYEE_ID);
		return time;
	}	


}
