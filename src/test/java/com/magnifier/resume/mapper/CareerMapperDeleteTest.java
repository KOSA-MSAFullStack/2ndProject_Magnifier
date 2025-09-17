package com.magnifier.resume.mapper;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.career.mapper.CareerMapper;

/**
 * 
 * @author 이상우
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CareerMapperDeleteTest {
	@Autowired
    private CareerMapper mapper;
	
    @Test
    public void modifyCareerTest() {
    	int id = 5;
    	mapper.deleteCareer(id);
    }
}
