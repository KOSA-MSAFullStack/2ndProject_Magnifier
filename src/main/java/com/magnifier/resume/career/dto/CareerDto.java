package com.magnifier.resume.career.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CareerDto {
	private int careerId;		// 경력사항_ID
	private int resumeId;		// 이력서_ID
	private String name;		// 회사명
	private LocalDate joinDate;	// 입사년월
	private LocalDate quitDate;	// 퇴사년월
	private String job;			// 직무
	private String department;	// 근무부서
	private String position;	// 직급/직책
}
