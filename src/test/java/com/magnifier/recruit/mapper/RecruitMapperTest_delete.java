// RecruitMapperTest_delete.java
// RecruitMapper 채용 공고 삭제(DELETE) 기능 테스트
/*
 * 설명:
 * - RecruitMapper의 deleteRecruit 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - 특정 채용 공고 ID 기준, DB에서 공고 삭제
 * - 삭제 성공 여부 및 삭제 후 공고 존재 여부 검증
 *
 * 주의:
 * - 이 테스트는 삭제할 공고가 DB에 미리 존재해야 함
 * - insertRecruit 메서드가 생성된 ID를 반환하도록 수정되면,
 *   테스트 내에서 공고를 등록하고 해당 ID를 사용하여 삭제를 검증할 수 있음.
 */

package com.magnifier.recruit.mapper;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-context.xml" })
@Log4j
@Transactional
public class RecruitMapperTest_delete {

    @Autowired
    private RecruitMapper mapper;

    // 채용 공고 삭제 테스트 메서드
    @Test
    public void testDeleteRecruit() throws SQLException {
        // given: 테스트용 채용 공고 데이터 생성 및 삽입
        RecruitDto newRecruit = new RecruitDto();
        newRecruit.setTitle("삭제 테스트 공고 제목");
        newRecruit.setContent("삭제 테스트 직무 내용");
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
        newRecruit.setContact("delete@test.com");
        newRecruit.setEnterpriseId(2001); // 테스트용 기업 ID

        mapper.insertRecruit(newRecruit);
        int recruitIdToDelete = newRecruit.getRecruitId();
        int enterpriseId = newRecruit.getEnterpriseId();

        // 삭제할 공고 DTO 설정
        RecruitDto deleteDto = new RecruitDto();
        deleteDto.setRecruitId(recruitIdToDelete);
        deleteDto.setEnterpriseId(enterpriseId);

        // when: mapper의 deleteRecruit 메서드 호출
        int result = mapper.deleteRecruit(deleteDto);

        // then: 실행 결과 확인
        // 1. delete 성공 시 1 반환 검증
        assertEquals(1, result);
        log.info("공고 삭제 성공. 결과: " + result);

        // 2. 삭제 후 공고 정보 다시 조회, null 반환 검증
        RecruitDto deletedRecruit = mapper.detailRecruit(recruitIdToDelete);
        assertNull(deletedRecruit);
        log.info("삭제 후 공고 존재 여부 확인: " + (deletedRecruit == null ? "삭제됨" : "삭제 안됨"));
        log.info("RecruitMapper deleteRecruit 테스트 성공");
    }
}