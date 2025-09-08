package com.magnifier.resume.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.resume.dto.ResumeDto;

@Mapper
public interface ResumeMapper {
	// 이력서 등록
	public void registerResume(ResumeDto dto);
	
	// 이력서 조회
	public List<ResumeDto> findResumesByMemberId(int memberId);
	
	// 이력서 수정
	public int modifyResume(ResumeDto dto);
	
	// 이력서 확인
	public int countResumeByUserId(int memberId);
}
