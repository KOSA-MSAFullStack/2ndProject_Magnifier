package com.magnifier.resume.service;

import java.util.List;

import com.magnifier.resume.dto.ResumeDto;

public interface ResumeService {
	// 이력서 등록
	public void registerResume(ResumeDto dto);
	
	// 개인회원 이력서 조회
	public List<ResumeDto> findResumesByMemberId(int memberId);
}
