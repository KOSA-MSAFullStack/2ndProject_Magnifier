package com.magnifier.resume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.mapper.ResumeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeMapper resumeMapper;
	
	@Override
	public void registerResume(ResumeDto resumeDto) {
		resumeMapper.registerResume(resumeDto);
	}

}
