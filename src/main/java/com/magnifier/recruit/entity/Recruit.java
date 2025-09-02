// Recruit.java
// Entity (DB 매핑용, 1:1 매핑), 내부에서만 사용, 외부 API에서는 노출 안하는 용도

package com.magnifier.recruit.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Recruit {
	private int recruitId;				// 채용 공고_ID
	private String title;				// 공고 제목
	private String content;				// 직무 내용
	private String careerCondition;		// 경력 조건
	private String education;			// 학력
	private String employeeType;		// 고용 형태	
	private String headCount;			// 모집인원
	private String workingArea;			// 근무 지역 
	private String salaryCondition;		// 임금 조건
	private String workingHours;		// 근무 시간
	private String workingType;			// 근무 형태
	private String insurance;			// 사회보험
	private String retirementSalary;	// 퇴직 급여
	private LocalDate deadLine;			// 접수 마감일
	private String step;				// 전형 단계
	private String contact;				// 담당자 연락처
	private int enterpriseId;			// 기업회원_ID
}
