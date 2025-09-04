// RecruitDto.java
// 채용 공고 데이터 전송 객체 (DTO)
/*
 * 설명:
 * - 클라이언트와 서버 간, 혹은 각 계층(Layer) 간 데이터 전송에 사용되는 객체
 * - 채용 공고와 관련된 데이터 담고 있으며, 화면에 필요한 정보 전달 시 주로 사용
 */

package com.magnifier.recruit.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecruitDto {
	private int recruitId;				// 채용 공고 ID (PK)
	private String title;				// 공고 제목
	private String content;				// 직무 내용
	private String careerCondition;		// 경력 조건
	private String education;			// 학력 조건
	private String employmentType;		// 고용 형태
	private String headCount;			// 모집 인원
	private String workingArea;			// 근무 지역
	private String salaryCondition;		// 임금 조건
	private String workingHours;		// 근무 시간
	private String workingType;			// 근무 형태
	private String insurance;			// 사회보험
	private String retirementSalary;	// 퇴직 급여
	private LocalDate deadLine;			// 접수 마감일
	private String step;				// 전형 절차
	private String contact;				// 담당자 연락처
	private int enterpriseId;			// 기업회원 ID (FK)
}
