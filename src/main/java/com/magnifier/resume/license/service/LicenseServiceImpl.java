package com.magnifier.resume.license.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.resume.license.dto.LicenseDto;
import com.magnifier.resume.license.mapper.LicenseMapper;

@Service
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private LicenseMapper mapper;

	// 자격사항 추가
	@Override
	public void insertLicense(LicenseDto dto) {
		mapper.insertLicense(dto);
	}

	// 자격사항 수정
	@Override
	public int modifyLicense(LicenseDto dto) {
		return mapper.modifyLicense(dto);
	}

	// 자격사항 삭제
	@Override
	public int deleteLicense(int id) {
		return mapper.deleteLicense(id);
	}
	
	
}
