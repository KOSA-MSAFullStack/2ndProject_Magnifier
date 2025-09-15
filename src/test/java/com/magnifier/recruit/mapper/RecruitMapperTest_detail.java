// RecruitMapperTest_detail.java
// RecruitMapper 채용 공고 상세 조회(SELECT) 기능 테스트
/*
 * 설명:
 * - RecruitMapper의 detailRecruit 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - 특정 채용 공고 ID 기준, 상세 정보 조회
 * - 조회된 공고 상세 내용 로그 출력
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
 
 // * author: 김기성
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml" })
 @Log4j
 @Transactional
 public class RecruitMapperTest_detail {
 
     @Autowired
     private RecruitMapper mapper;
 
     // 채용 공고 상세 조회 테스트 메서드
     @Test
     public void testDetailRecruit() throws SQLException {
         // given: 테스트용 채용 공고 데이터 생성 및 삽입
         RecruitDto newRecruit = new RecruitDto();
         newRecruit.setTitle("테스트 공고");
         newRecruit.setContent("테스트 내용");
         newRecruit.setCareerCondition("신입");
         newRecruit.setEducation("대졸");
         newRecruit.setEmploymentType("정규직");
         newRecruit.setHeadCount("0명");
         newRecruit.setWorkingArea("서울");
         newRecruit.setSalaryCondition("회사내규");
         newRecruit.setWorkingHours("주 5일");
         newRecruit.setWorkingType("재택");
         newRecruit.setInsurance("4대보험");
         newRecruit.setRetirementSalary("퇴직금");
         newRecruit.setDeadLine(LocalDate.of(2025, 12, 31));
         newRecruit.setStep("서류-면접");
         newRecruit.setContact("010-1234-5678");
         newRecruit.setEnterpriseId(1);
 
         mapper.insertRecruit(newRecruit); // 채용 공고 삽입
         int recruitId = newRecruit.getRecruitId(); // 삽입된 공고의 ID 획득
 
         // when: mapper의 detailRecruit 메서드 호출
         RecruitDto recruit = mapper.detailRecruit(recruitId);
 
         // then: 실행 결과 확인
         // 1. 조회된 객체가 null이 아닌지 검증
         assertNotNull(recruit);
         
         // 2. 조회된 공고 ID가 요청한 ID와 일치하는지 검증
         assertEquals(recruitId, recruit.getRecruitId());
 
         log.info("----------");
         log.info("조회된 공고 정보: " + recruit);
         log.info("RecruitMapper detailRecruit 테스트 성공");
     }
 }