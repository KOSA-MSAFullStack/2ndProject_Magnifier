package com.magnifier.resume.mapper;

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
public class ResumeDetailMapperTest {
	
	@Autowired
	private ResumeMapper mapper;
	
	@Test
    public void testFindResumesByMemberId() {
        int memberId = 3;
        
        List<ResumeDto> resumes = mapper.findResumesByMemberId(memberId);
        
        log.info("--- 회원 ID " + memberId + "에 대한 이력서 조회 결과 ---");
        for (ResumeDto resume : resumes) {
            log.info(resume);
            log.info("Career: " + resume.getCareerList());
            log.info("License: " + resume.getLicenseList());
        }
    }
}
