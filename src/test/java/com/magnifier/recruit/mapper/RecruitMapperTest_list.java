// RecruitMapperTest_list.java
// RecruitMapper 전체 채용 공고 조회(SELECT) 기능 테스트
/*
 * 설명:
 * - RecruitMapper의 getRecruitList 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - getRecruitList 메서드 호출, DB 전체 채용 공고 목록 조회
 * - 조회된 목록의 내용과 전체 개수 로그 출력
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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional
public class RecruitMapperTest_list {

    @Autowired
    private RecruitMapper mapper;

    // 전체 채용 공고 목록 조회 테스트 메서드
    @Test
    public void testGetList() throws SQLException {
        // when: mapper의 getRecruitList 메서드 호출
        List<RecruitDto> list = mapper.getRecruitList();

        // then: 실행 결과 확인
        // 1. 리스트가 null이 아닌지 검증
        assertNotNull(list);

        // 2. forEach와 람다 표현식 사용, 목록 순회하며 각 공고 정보 로그 출력
        list.forEach(recruit -> log.info(recruit));

        log.info("----------");
        log.info("전체 공고 수: " + list.size());
        log.info("RecruitMapper getRecruitList 테스트 성공");
    }
}
