package com.magnifier.resume.license.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.resume.license.dto.LicenseDto;
import com.magnifier.resume.license.mapper.LicenseMapper;

@Service
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private LicenseMapper mapper;

	@Override
	public void insertLicense(LicenseDto dto) {
		mapper.insertLicense(dto);
	}
	
	
}
