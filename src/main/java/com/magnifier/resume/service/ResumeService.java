package com.magnifier.resume.service;

import java.util.List;

import com.magnifier.resume.dto.ResumeDto;

public interface ResumeService {
	// 이력서 등록
	public void registerResume(ResumeDto dto);
	
	// 이력서 조회
	public List<ResumeDto> findResumesByMemberId(int memberId);
	
	// 이력서 수정
	public int modifyResume(ResumeDto dto);
	
	// 이력서 확인
	public boolean hasResume(int memberId);
}
