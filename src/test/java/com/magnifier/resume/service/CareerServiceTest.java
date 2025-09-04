package com.magnifier.resume.service;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.career.service.CareerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CareerServiceTest {

	@Autowired
	private CareerService service;
	
	@Test
	public void insertCareer() {
		CareerDto dto = new CareerDto();
		dto.setResumeId(1);
    	dto.setName("세림티에스지");
    	dto.setJoinDate(LocalDate.of(1980,03,01));
    	dto.setQuitDate(LocalDate.of(1986,02,01));
    	dto.setJob("백엔드개발");
    	dto.setDepartment("개발부");
    	dto.setPosition("사원");
    	service.insertCareer(dto);
	}
}
