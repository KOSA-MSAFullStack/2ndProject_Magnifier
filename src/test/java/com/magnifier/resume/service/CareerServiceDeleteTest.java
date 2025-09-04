package com.magnifier.resume.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.career.service.CareerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CareerServiceDeleteTest {

	@Autowired
	private CareerService service;
	
	@Test
	public void deleteCareer() {
		int id = 6;
    	service.deleteCareer(id);
	}
}
