package com.magnifier.resume.license.service;

import com.magnifier.resume.license.dto.LicenseDto;

public interface LicenseService {
	// 자격사항 추가
	public void insertLicense(LicenseDto dto);
	
	// 자격사항 수정
	public int modifyLicense(LicenseDto dto);
	
	// 자격사항 삭제
	public int deleteLicense(int id);
}
