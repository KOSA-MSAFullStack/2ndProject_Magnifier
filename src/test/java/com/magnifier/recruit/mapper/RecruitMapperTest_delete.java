// RecruitMapperTest_delete.java
// 역할: RecruitMapper.deleteRecruit() 메서드 테스트
/*
 * 설명 및 주요 기능
 * - 채용 공고 삭제 기능 검증
 * - Spring-test, JUnit, Log4j 라이브러리 활용
 * - MyBatis 매퍼 인터페이스와 XML 쿼리 연동 테스트
 */

package com.magnifier.recruit.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertNotNull;

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
public class RecruitMapperTest_delete {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testDeleteRecruit() throws SQLException {
        // 테스트를 위해 먼저 공고를 등록 (테스트 환경에 따라 이 부분은 조정 필요)
        // 실제 테스트 환경에 맞는 유효한 recruitId와 enterpriseId로 변경 필요
        // 또는 테스트용 데이터 삽입 로직 추가
        RecruitDto testRecruit = new RecruitDto();
        // testRecruit.setRecruitId(999); // DB에서 자동 생성되므로 설정하지 않음
        testRecruit.setTitle("삭제 테스트용 공고");
        testRecruit.setContent("삭제 테스트용 내용");
        testRecruit.setCareerCondition("무관");
        testRecruit.setEducation("무관");
        testRecruit.setEmployeeType("계약직");
        testRecruit.setHeadCount("1명");
        testRecruit.setWorkingArea("서울");
        testRecruit.setSalaryCondition("협의");
        testRecruit.setWorkingHours("주 5일");
        testRecruit.setWorkingType("풀타임");
        testRecruit.setInsurance("4대보험");
        testRecruit.setRetirementSalary("퇴직금");
        testRecruit.setDeadLine(java.time.LocalDate.now().plusDays(7));
        testRecruit.setStep("서류");
        testRecruit.setContact("010-1234-5678");
        testRecruit.setEnterpriseId(2001); // 예시 기업 ID

        // 공고 등록 (삭제 테스트를 위해)
        mapper.insertRecruit(testRecruit);
        // insert 후 생성된 recruitId를 가져오는 로직이 필요할 수 있음 (useGeneratedKeys 등)
        // 현재는 DTO에 recruitId가 없으므로, detailRecruit로 찾거나,
        // 테스트용으로 미리 존재하는 ID를 사용해야 함.
        // 여기서는 임시로 1번 ID를 사용한다고 가정 (실제 DB 상태에 따라 변경 필요)
        int recruitIdToDelete = 1; // 실제 삭제할 유효한 ID로 변경

        // 삭제할 공고의 enterpriseId도 DTO에 설정
        RecruitDto deleteDto = new RecruitDto();
        deleteDto.setRecruitId(recruitIdToDelete);
        deleteDto.setEnterpriseId(2001); // 실제 기업 ID로 변경

        // delete 실행
        int result = mapper.deleteRecruit(deleteDto);

        // 결과 확인 (delete 성공 시 1 반환)
        assertEquals(1, result);

        // 삭제 후 공고 정보 다시 조회하여 삭제 확인 (null이 반환되어야 함)
        RecruitDto deletedRecruit = mapper.detailRecruit(recruitIdToDelete);
        assertNull(deletedRecruit);
    }
}
