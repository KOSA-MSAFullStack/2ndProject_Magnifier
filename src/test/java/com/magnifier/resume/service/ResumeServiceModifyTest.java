package com.magnifier.resume.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.dto.ResumeDto;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ResumeServiceModifyTest {

	@Autowired
    private ResumeService service;
	
	@Test
    public void testFindResumesByMemberId() {
		ResumeDto dto = new ResumeDto();
		dto.setResumeId(2);
    	dto.setMemberId(1);
    	dto.setTitle("titleTest");
    	dto.setSchoolType("고등학교");
    	dto.setSchoolName("서대전고등학교");
    	dto.setGraduateStatus("졸업");
    	dto.setEnterDate(LocalDate.of(1980,03,01));
    	dto.setGraduateDate(LocalDate.of(1986,02,01));
    	service.modifyResume(dto);
    }
}
