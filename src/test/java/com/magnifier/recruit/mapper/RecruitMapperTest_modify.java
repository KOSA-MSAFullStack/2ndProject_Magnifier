// RecruitMapperTest_modify.java
// RecruitMapper Update(U) 테스트
// 공고 수정 테스트

package com.magnifier.recruit.mapper;

import java.time.LocalDate;
import java.sql.SQLException;
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
public class RecruitMapperTest_modify {
    @Autowired
    private RecruitMapper recruitMapper;

    @Test
    public void updateRecruit() throws SQLException {
        RecruitDto recruit = new RecruitDto();
        recruit.setRecruitId(1); 
        recruit.setTitle("시니어 안내원 모집");     // 경비원 --> 안내원
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
        recruit.setEnterpriseId(2001);

        int count = recruitMapper.updateRecruit(recruit);
        log.info("Update COUNT" + count);              
    }
}
