package com.magnifier.resume.service;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.dto.ResumeDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ResumeServiceTest {

	@Autowired
    private ResumeService service;

	@Test
    public void testRegisterResume() {
        ResumeDto dto = new ResumeDto();
        dto.setMemberId(1);
        dto.setTitle("titleTest");
        dto.setSchoolType("중학교");
        dto.setSchoolName("서대전중학교");
        dto.setGraduateStatus("졸업");
        dto.setEnterDate(LocalDate.of(1986,03,01));
        dto.setGraduateDate(LocalDate.of(1989,02,01));
        service.registerResume(dto);
    }

}
