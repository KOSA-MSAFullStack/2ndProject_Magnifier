package com.magnifier.resume.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.resume.license.service.LicenseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class LicenseServiceDeleteTest {

	@Autowired
	private LicenseService service;
	
	@Test
	public void deleteLicense() {
		int id = 6;
    	service.deleteLicense(id);
	}
}
