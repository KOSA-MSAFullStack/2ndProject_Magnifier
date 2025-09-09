// RecruitMapperTest_listById.java
// RecruitMapper 기업별 채용 공고 조회(SELECT) 기능 테스트
/*
 * 설명:
 * - RecruitMapper의 getRecruitListById 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - 특정 기업 ID 기준, 해당 기업 채용 공고 목록 조회
 * - 조회된 목록 내용 & 전체 개수 로그 출력
 */

package com.magnifier.recruit.mapper;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.List;
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
public class RecruitMapperTest_listById {

    @Autowired
    private RecruitMapper mapper;

    // 특정 기업의 채용 공고 목록 조회 테스트 메서드
    @Test
    public void testGetListById() throws SQLException {
        // given: 테스트용 기업 ID 설정
        int enterpriseId = 2001; // 실제 존재하는 기업 ID로 테스트 권장

        // when: mapper의 getRecruitListById 메서드 호출
        List<RecruitDto> list = mapper.getRecruitListById(enterpriseId);

        // then: 실행 결과 확인
        // 1. 리스트가 null이 아닌지 검증
        assertNotNull(list);

        // 2. forEach와 람다 표현식 사용, 목록 순회하며 각 공고 정보 로그 출력
        list.forEach(recruit -> log.info(recruit));

        log.info("----------");
        log.info("기업 ID " + enterpriseId + "의 전체 공고 수: " + list.size());
        log.info("RecruitMapper getRecruitListById 테스트 성공");
    }
}
