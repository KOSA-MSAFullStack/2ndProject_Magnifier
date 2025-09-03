package com.magnifier.resume.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.resume.dto.ResumeDto;

@Mapper
public interface ResumeMapper {
	public void registerResume(ResumeDto resumeDto);
}
