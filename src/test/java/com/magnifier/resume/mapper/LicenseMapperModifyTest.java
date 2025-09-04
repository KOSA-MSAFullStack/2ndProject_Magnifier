package com.magnifier.resume.mapper;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.license.dto.LicenseDto;
import com.magnifier.resume.license.mapper.LicenseMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class LicenseMapperModifyTest {
	@Autowired
    private LicenseMapper mapper;
	
    @Test
    public void modifyLicenseTest() {
    	LicenseDto dto = new LicenseDto();
    	dto.setLicenseId(1);
    	dto.setResumeId(1);
    	dto.setName("컴활1급");
    	dto.setPublisher("큐넷");
    	dto.setPassDate(LocalDate.of(1996,03,01));
    	mapper.modifyLicense(dto);
    }
}
