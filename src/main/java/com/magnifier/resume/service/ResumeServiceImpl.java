package com.magnifier.resume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.mapper.ResumeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeMapper mapper;
	
	@Override
	public void registerResume(ResumeDto resumeDto) {
		mapper.registerResume(resumeDto);
	}

	@Override
	public List<ResumeDto> findResumesByMemberId(int memberId) {
		return mapper.findResumesByMemberId(memberId);
	}

	@Override
	public int modifyResume(ResumeDto resumeDto) {
		return mapper.modifyResume(resumeDto);
	}

	@Override
	public boolean hasResume(int memberId) {
		// Mapper 계층의 메소드를 호출하여 이력서 개수를 가져옴
        int resumeCount = mapper.countResumeByUserId(memberId);
        
        // 이력서 개수가 1개 이상이면 true, 아니면 false 반환
        return resumeCount > 0; 
	}

}
