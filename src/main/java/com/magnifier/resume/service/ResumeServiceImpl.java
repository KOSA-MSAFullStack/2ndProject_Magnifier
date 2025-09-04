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

}
