package com.magnifier.resume.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	@Transactional
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
	@Transactional
	public int modifyResume(ResumeDto resumeDto) {
		// 1. 메인 이력서 정보 수정
	    int result = resumeMapper.modifyResume(resumeDto);
	    
	    // 2. 경력사항 목록 처리 (추가 , 수정, 삭제)
	    // 2-1. 기존 경력 ID 목록을 DB에서 모두 조회
	    List<Integer> existingCareerIds = careerMapper.findCareerIdsByResumeId(resumeDto.getResumeId());
	    
	    // 2-2. 클라이언트에서 넘어온 경력 ID 목록을 수집 (수정된 항목)
	    List<Integer> receivedCareerIds = new ArrayList<>();
	    if (resumeDto.getCareerList() != null) {
	        for (CareerDto career : resumeDto.getCareerList()) {
	            career.setResumeId(resumeDto.getResumeId());
	            System.out.println("추가 또는 수정" + career);
	            if (career.getCareerId() == 0) {
	            	// 신규 항목: careerId가 0인 경우 INSERT
	            	careerMapper.insertCareer(career);
	            } else {
	            	// 기존 항목: careerId가 있는 경우 UPDATE
	            	careerMapper.modifyCareer(career);
	            	receivedCareerIds.add(career.getCareerId());
	            }
	        }
	    }
	    
	    // 2-3. 삭제 대상 경력 ID를 판별하여 삭제
	    // 기존 ID 목록에 있지만, 클라이언트가 보낸 ID 목록에 없는 ID를 찾음
	    for (Integer existingId : existingCareerIds) {
	        if (!receivedCareerIds.contains(existingId)) {
	            careerMapper.deleteCareer(existingId);
	        }
	    }

	    // 3. 자격사항 처리 (추가, 수정, 삭제)
	    // 3-1. 기존 자격 ID 목록을 DB에서 모두 조회
	    List<Integer> existingLicenseIds = licenseMapper.findLicenseIdsByResumeId(resumeDto.getResumeId());
	    
	    // 3-2. 클라이언트에서 넘어온 자격 ID 목록을 수집 (수정된 항목)
	    List<Integer> receivedLicenseIds = new ArrayList<>();
	    if (resumeDto.getLicenseList() != null) {
	        for (LicenseDto license : resumeDto.getLicenseList()) {
	            license.setResumeId(resumeDto.getResumeId());
	            if (license.getLicenseId() == 0) {
	                // 신규 항목: licenseId가 0인 경우 INSERT
	                licenseMapper.insertLicense(license);
	            } else {
	                // 기존 항목: licenseId가 있는 경우 UPDATE
	                licenseMapper.modifyLicense(license);
	                receivedLicenseIds.add(license.getLicenseId());
	            }
	        }
	    }

	    // 3-3. 삭제 대상 자격 ID를 판별하여 삭제
	    // 기존 ID 목록에 있지만, 클라이언트가 보낸 ID 목록에 없는 ID를 찾음
	    for (Integer existingId : existingLicenseIds) {
	        if (!receivedLicenseIds.contains(existingId)) {
	            licenseMapper.deleteLicense(existingId);
	        }
	    }

	    return result;
	}

	@Override
	public ResumeDto findResumesByMemberId(int memberId) {
		return resumeMapper.findResumesByMemberId(memberId);
	}

	@Override
	public boolean hasResume(int memberId) {
		// Mapper 계층의 메소드를 호출하여 이력서 개수를 가져옴
        int resumeCount = resumeMapper.countResumeByUserId(memberId);
        
        // 이력서 개수가 1개 이상이면 true, 아니면 false 반환
        return resumeCount > 0; 
	}

}
