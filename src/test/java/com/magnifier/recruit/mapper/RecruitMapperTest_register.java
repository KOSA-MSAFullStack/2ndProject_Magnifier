// RecruitMapperTest_register.java
// RecruitMapper Insert(C) 테스트

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
    private RecruitMapper recruitMapper;

    @Test
    public void insertRecruit() throws SQLException {
        // DTO 객체 생성
        RecruitDto dto = new RecruitDto();
        dto.setRecruitId(1);  // PK 직접 입력 (DB에서 auto_increment면 제거 가능)
        dto.setTitle("시니어 경비원 모집");
        dto.setContent("아파트 단지 보안 및 출입 관리. 신체 건강한 60세 이상 지원 가능");
        dto.setCareerCondition("무관 (경비 경험자 우대)");
        dto.setEducation("무관");
        dto.setEmployeeType("계약직");
        dto.setHeadCount("3명");
        dto.setWorkingArea("서울 송파구");
        dto.setSalaryCondition("월 220만원 이상");
        dto.setWorkingHours("주 5일, 09:00~18:00 (교대근무 가능)");
        dto.setWorkingType("풀타임");
        dto.setInsurance("4대보험 가입");
        dto.setRetirementSalary("퇴직금 별도 지급");
        dto.setDeadLine(LocalDate.of(2025, 9, 30));
        dto.setStep("서류 심사 → 면접 → 채용");
        dto.setContact("02-1234-5678");
        dto.setEnterpriseId(2001); // 기업회원_ID 예시

        // insert 실행
        int result = recruitMapper.insertRecruit(dto);

        // 결과 확인 (insert 성공 시 1 반환)
        assertEquals(1, result);
    }
}