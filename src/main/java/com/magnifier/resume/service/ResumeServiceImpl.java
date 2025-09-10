package com.magnifier.resume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.career.mapper.CareerMapper;
import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.license.dto.LicenseDto;
import com.magnifier.resume.license.mapper.LicenseMapper;
import com.magnifier.resume.mapper.ResumeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private CareerMapper careerMapper;
	
	@Autowired
	private LicenseMapper licenseMapper;
	
	@Transactional
	@Override
	public void registerResume(ResumeDto resumeDto) {
		// 1. 이력서 등록: 이 시점에서 resumeDto.getResumeId()는 null 또는 0이지만,
	    //    매퍼가 실행되면 selectKey를 통해 resumeId가 자동으로 채워집니다.
		resumeMapper.registerResume(resumeDto);

	    // 2. 생성된 이력서 ID 가져오기: 매퍼 실행 후 DTO에 채워진 ID를 가져옴.
	    int resumeId = resumeDto.getResumeId();

	    // 3. 경력사항 등록
	    if (resumeDto.getCareerList() != null && !resumeDto.getCareerList().isEmpty()) {
	        for (CareerDto career : resumeDto.getCareerList()) {
	            career.setResumeId(resumeId);
	            careerMapper.insertCareer(career);
	        }
	    }

	    // 4. 자격사항 등록
	    if (resumeDto.getLicenseList() != null && !resumeDto.getLicenseList().isEmpty()) {
	        for (LicenseDto license : resumeDto.getLicenseList()) {
	            license.setResumeId(resumeId);
	            licenseMapper.insertLicense(license);
	        }
	    }
	}

	@Override
	public ResumeDto findResumesByMemberId(int memberId) {
		return resumeMapper.findResumesByMemberId(memberId);
	}

	@Override
	public int modifyResume(ResumeDto resumeDto) {
		return resumeMapper.modifyResume(resumeDto);
	}

	@Override
	public boolean hasResume(int memberId) {
		// Mapper 계층의 메소드를 호출하여 이력서 개수를 가져옴
        int resumeCount = resumeMapper.countResumeByUserId(memberId);
        
        // 이력서 개수가 1개 이상이면 true, 아니면 false 반환
        return resumeCount > 0; 
	}

}
