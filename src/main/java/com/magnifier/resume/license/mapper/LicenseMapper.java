package com.magnifier.resume.license.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.resume.license.dto.LicenseDto;

@Mapper
public interface LicenseMapper {
	// 자격사항 추가
	public void insertLicense(LicenseDto dto);
	
	// 자격사항 수정
	public int modifyLicense(LicenseDto dto);
	
	// 자격사항 삭제
	public int deleteLicense(int id);
	
	// 이력서에 있는 자격사항 리스트 조회
	public List<Integer> findLicenseIdsByResumeId(int resumeId);
}
