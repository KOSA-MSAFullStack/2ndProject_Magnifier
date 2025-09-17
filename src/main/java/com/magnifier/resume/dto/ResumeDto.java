package com.magnifier.resume.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magnifier.member.entity.Member;
import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.license.dto.LicenseDto;

import lombok.Data;

/**
 * 
 * @author 이상우
 *
 */
@Data
public class ResumeDto {
	private int resumeId; 					// 이력서_ID
	private int memberId;					// 회원_ID
	private String title;					// 이력서 제목
	private String schoolType;				// 학교구분
	private String schoolName;				// 학교명
	private String graduateStatus;			// 졸업여부
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDate enterDate;			// 입학년월
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDate graduateDate;			// 졸업년월
	private Member member;					// 회원 정보
	private List<CareerDto> careerList;		// 경력사항 리스트
	private List<LicenseDto> licenseList;	// 자격사항 리스트
}