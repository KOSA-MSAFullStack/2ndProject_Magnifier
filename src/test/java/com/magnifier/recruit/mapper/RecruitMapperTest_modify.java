// RecruitMapperTest_modify.java
// RecruitMapper 채용 공고 수정(UPDATE) 기능 테스트
/*
 * 설명:
 * - RecruitMapper의 updateRecruit 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - 기존 채용 공고 정보 조회
 * - 조회된 공고의 특정 필드 값 수정
 * - 수정된 공고 정보로 DB 업데이트 실행
 * - 업데이트 성공 여부 및 수정된 내용의 정확성 검증
 */

 package com.magnifier.recruit.mapper;

 import org.springframework.transaction.annotation.Transactional;
 
 
 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertNotNull;
 import java.sql.SQLException;
 import java.time.LocalDate;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.test.context.ContextConfiguration;
 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 import com.magnifier.recruit.dto.RecruitDto;
 import lombok.extern.log4j.Log4j;
 
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml" })
 @Log4j
 @Transactional
 public class RecruitMapperTest_modify {
 
     @Autowired
     private RecruitMapper mapper;
 
     // 채용 공고 수정 테스트 메서드
     @Test
     public void testUpdateRecruit() throws SQLException {
         // given: 테스트용 채용 공고 데이터 생성 및 삽입
         RecruitDto newRecruit = new RecruitDto();
         newRecruit.setTitle("테스트 공고 제목");
         newRecruit.setContent("테스트 직무 내용");
         newRecruit.setCareerCondition("신입");
         newRecruit.setEducation("학력무관");
         newRecruit.setEmploymentType("계약직");
         newRecruit.setHeadCount("1명");
         newRecruit.setWorkingArea("서울");
         newRecruit.setSalaryCondition("협의");
         newRecruit.setWorkingHours("주 5일");
         newRecruit.setWorkingType("재택");
         newRecruit.setInsurance("없음");
         newRecruit.setRetirementSalary("없음");
         newRecruit.setDeadLine(LocalDate.of(2025, 12, 31));
         newRecruit.setStep("서류");
         newRecruit.setContact("test@test.com");
         newRecruit.setEnterpriseId(2001); // 테스트용 기업 ID
 
         mapper.insertRecruit(newRecruit); // 공고 삽입, recruitId는 DTO에 자동 설정됨
         int recruitId = newRecruit.getRecruitId(); // 삽입된 recruitId 가져오기
         int enterpriseId = newRecruit.getEnterpriseId(); // 삽입된 enterpriseId 가져오기
 
         // 1. 기존 공고 정보 조회 (수정 전 확인용)
         RecruitDto originalRecruit = mapper.detailRecruit(recruitId);
         assertNotNull(originalRecruit);
         log.info("수정 전 공고: " + originalRecruit);
 
         // 2. 수정할 DTO 객체 생성 및 값 설정
         RecruitDto updatedRecruit = new RecruitDto();
         updatedRecruit.setRecruitId(recruitId);
         updatedRecruit.setEnterpriseId(enterpriseId); // 업데이트 시 WHERE 조건에 필요
 
         // 수정할 필드 값 설정
         updatedRecruit.setTitle("수정된 공고 제목");
         updatedRecruit.setContent("수정된 직무 내용입니다. 경력 무관, 학력 무관.");
         updatedRecruit.setCareerCondition("경력 5년 이상");
         updatedRecruit.setEducation("대졸 이상");
         updatedRecruit.setEmploymentType("정규직");
         updatedRecruit.setHeadCount("5명");
         updatedRecruit.setWorkingArea("서울 강남구");
         updatedRecruit.setSalaryCondition("월 300만원 이상");
         updatedRecruit.setWorkingHours("주 5일, 10:00~19:00");
         updatedRecruit.setWorkingType("풀타임");
         updatedRecruit.setInsurance("4대보험 가입");
         updatedRecruit.setRetirementSalary("퇴직금 별도 지급");
         updatedRecruit.setDeadLine(LocalDate.of(2026, 1, 31));
         updatedRecruit.setStep("서류 → 1차 면접 → 2차 면접 → 최종 합격");
         updatedRecruit.setContact("02-9876-5432");
 
         // when: mapper의 updateRecruit 메서드 호출
         int result = mapper.updateRecruit(updatedRecruit);
 
         // then: 실행 결과 확인
         // 1. update 성공 시 1 반환 검증
         assertEquals(1, result);
 
         // 2. 수정 후 공고 정보 다시 조회하여 변경 확인
         RecruitDto afterUpdateRecruit = mapper.detailRecruit(recruitId);
         assertNotNull(afterUpdateRecruit);
         log.info("수정 후 공고: " + afterUpdateRecruit);
 
         // 3. 수정된 내용이 반영되었는지 일부 필드 검증
         assertEquals("수정된 공고 제목", afterUpdateRecruit.getTitle());
         assertEquals("정규직", afterUpdateRecruit.getEmploymentType());
         log.info("RecruitMapper updateRecruit 테스트 성공");
     }
 }
 