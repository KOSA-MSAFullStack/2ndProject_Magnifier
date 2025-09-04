// RecruitMapperTest_register.java
// RecruitMapper 채용 공고 등록(INSERT) 기능 테스트
/*
 * 설명:
 * - RecruitMapper의 insertRecruit 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - RecruitDto 객체 생성 및 테스트 데이터 설정
 * - insertRecruit 메서드 호출, DB 채용 공고 등록
 * - 등록 성공 여부 확인 (결과 값 1인지 검증)
 */

package com.magnifier.recruit.mapper;

import static org.junit.Assert.assertEquals;
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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RecruitMapperTest_register {

    @Autowired
    private RecruitMapper mapper;

    // 채용 공고 등록 테스트 메서드
    @Test
    public void insertRecruit() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성 및 데이터 설정
        RecruitDto recruit = new RecruitDto();
        recruit.setTitle("시니어 경비원 모집");
        recruit.setContent("아파트 단지 보안 및 출입 관리. 신체 건강한 60세 이상 지원 가능");
        recruit.setCareerCondition("무관 (경비 경험자 우대)");
        recruit.setEducation("무관");
        recruit.setEmployeeType("계약직");
        recruit.setHeadCount("3명");
        recruit.setWorkingArea("서울 송파구");
        recruit.setSalaryCondition("월 220만원 이상");
        recruit.setWorkingHours("주 5일, 09:00~18:00 (교대근무 가능)");
        recruit.setWorkingType("풀타임");
        recruit.setInsurance("4대보험 가입");
        recruit.setRetirementSalary("퇴직금 별도 지급");
        recruit.setDeadLine(LocalDate.of(2025, 9, 30));
        recruit.setStep("서류 심사 → 면접 → 채용");
        recruit.setContact("02-1234-5678");
        recruit.setEnterpriseId(2001); // FK, 기업회원 ID 예시

        // when: mapper의 insertRecruit 메서드 호출
        int result = mapper.insertRecruit(recruit);

        // then: 실행 결과 확인 (insert 성공 시 1 반환)
        assertEquals(1, result);
        log.info("RecruitMapper insertRecruit 테스트 성공, 결과: " + result);
    }
}
