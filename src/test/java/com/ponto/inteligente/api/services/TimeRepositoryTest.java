package com.ponto.inteligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ponto.inteligente.api.entities.Time;
import com.ponto.inteligente.api.repositories.TimeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TimeRepositoryTest {
	
	@MockBean
	private TimeRepository timeRepository;

	@Autowired
	private TimeService timeService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.timeRepository.findByEmployeeId(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new PageImpl<Time>(new ArrayList<Time>()));
		BDDMockito.given(this.timeRepository.findOne(Mockito.anyLong())).willReturn(new Time());
		BDDMockito.given(this.timeRepository.save(Mockito.any(Time.class))).willReturn(new Time());
	}

	@Test
	public void testFindTimeByEmployeeId() {
		Page<Time> time = this.timeService.findByEmployeeId(1L, new PageRequest(0, 10));

		assertNotNull(time);
	}

	@Test
	public void testFindTimeById() {
		Optional<Time> lancamento = this.timeService.findById(1L);

		assertTrue(lancamento.isPresent());
	}

	@Test
	public void testPersistTime() {
		Time time = this.timeService.persist(new Time());

		assertNotNull(time);
	}

}
