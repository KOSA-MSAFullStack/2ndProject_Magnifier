package com.magnifier.resume.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.license.mapper.LicenseMapper;

/**
 * 
 * @author 이상우
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class LicenseMapperDeleteTest {
	@Autowired
    private LicenseMapper mapper;
	
    @Test
    public void deleteLicenseTest() {
    	int id = 5;
    	mapper.deleteLicense(id);
    }
}
