// RecruitMapperTest_modify.java
// 역할: RecruitMapper.updateRecruit() 메서드 테스트
/*
 * 설명 및 주요 기능
 * - 채용 공고 수정 기능 검증
 * - Spring-test, JUnit, Log4j 라이브러리 활용
 * - MyBatis 매퍼 인터페이스와 XML 쿼리 연동 테스트
 */

package com.magnifier.recruit.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

import com.magnifier.recruit.dto.RecruitDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RecruitMapperTest_modify {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testUpdateRecruit() throws SQLException {
        // 테스트할 채용 공고 ID (실제 DB에 존재하는 ID로 설정)
        // 실제 테스트 환경에 맞는 유효한 recruitId와 enterpriseId로 변경 필요
        int recruitId = 1;
        int enterpriseId = 2001; // 예시 기업 ID

        // 기존 공고 정보 조회 (수정 전 확인용)
        RecruitDto originalRecruit = mapper.detailRecruit(recruitId);
        assertNotNull(originalRecruit);
        log.info("수정 전 공고: " + originalRecruit);

        // 수정할 DTO 객체 생성 및 값 설정
        RecruitDto updatedRecruit = new RecruitDto();
        updatedRecruit.setRecruitId(recruitId);
        updatedRecruit.setEnterpriseId(enterpriseId); // 업데이트 시 필요
        updatedRecruit.setTitle("수정된 공고 제목");
        updatedRecruit.setContent("수정된 직무 내용입니다. 경력 무관, 학력 무관.");
        updatedRecruit.setCareerCondition("경력 5년 이상");
        updatedRecruit.setEducation("대졸 이상");
        updatedRecruit.setEmployeeType("정규직");
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

        // update 실행
        int result = mapper.updateRecruit(updatedRecruit);

        // 결과 확인 (update 성공 시 1 반환)
        assertEquals(1, result);

        // 수정 후 공고 정보 다시 조회하여 변경 확인
        RecruitDto afterUpdateRecruit = mapper.detailRecruit(recruitId);
        assertNotNull(afterUpdateRecruit);
        log.info("수정 후 공고: " + afterUpdateRecruit);

        // 수정된 내용이 반영되었는지 일부 필드 검증
        assertEquals("수정된 공고 제목", afterUpdateRecruit.getTitle());
        assertEquals("정규직", afterUpdateRecruit.getEmployeeType());
    }
}
