// RecruitMapperTest_register.java
// RecruitMapper Insert(C) 테스트
// 채용 공고 등록 (C, Insert) - 기업회원

package com.magnifier.recruit.mapper;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.sql.SQLException;
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
public class RecruitMapperTest_register {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void insertRecruit() throws SQLException {
        // DTO 객체 생성
        RecruitDto recruit = new RecruitDto();
        recruit.setRecruitId(1);  // PK는 DB에서 자동 생성되므로 테스트 시에는 주석 처리
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
        recruit.setEnterpriseId(2001); // 기업회원_ID 예시

        // insert 실행
        int result = mapper.insertRecruit(recruit);

        // 결과 확인 (insert 성공 시 1 반환)
        assertEquals(1, result);
    }
}