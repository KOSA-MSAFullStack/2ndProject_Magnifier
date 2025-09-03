package com.magnifier.resume.license.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.resume.license.dto.LicenseDto;

@Mapper
public interface LicenseMapper {
	public void insertLicense(LicenseDto dto);
}
