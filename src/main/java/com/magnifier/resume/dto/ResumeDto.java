package com.magnifier.resume.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResumeDto {
	private int resumeId; 			// 이력서_ID
	private int memberId;			// 회원_ID
	private String title;			// 이력서 제목
	private String schoolType;		// 학교구분
	private String schoolName;		// 학교명
	private String graduateStatus;	// 졸업여부
	private LocalDate enterDate;	// 입학년월
	private LocalDate graduateDate;	// 졸업년월
}
