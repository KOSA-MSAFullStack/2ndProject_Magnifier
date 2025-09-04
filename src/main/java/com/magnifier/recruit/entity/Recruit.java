// Recruit.java
// DB의 RECRUIT 테이블과 매핑되는 엔티티 클래스
/*
 * 설명:
 * - DB의 RECRUIT 테이블의 한 행(Row) 나타내는 객체
 * - DB와 직접 통신, 데이터의 영속성(Persistence) 관리하는 데 사용
 * - 일반적으로 비즈니스 로직 포함 x, 순수한 데이터 객체로 사용
 */

package com.magnifier.recruit.entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Recruit {
	private int recruitId;				// 채용 공고 ID (PK)
	private String title;				// 공고 제목
	private String content;				// 직무 내용
	private String careerCondition;		// 경력 조건
	private String education;			// 학력 조건
	private String employeeType;		// 고용 형태
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
