// RecruitMapperTest_detail.java
// 역할: RecruitMapper.detailRecruit() 메서드 테스트
/*
 * 설명 및 주요 기능
 * - 채용 공고 상세 조회 기능 검증
 * - Spring-test, JUnit, Log4j 라이브러리 활용
 * - MyBatis 매퍼 인터페이스와 XML 쿼리 연동 테스트
 */

package com.magnifier.recruit.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

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
public class RecruitMapperTest_detail {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testDetailRecruit() throws SQLException {
        // 테스트할 채용 공고 ID (실제 DB에 존재하는 ID로 설정)
        // 실제 테스트 환경에 맞는 유효한 recruitId로 변경 필요
        int recruitId = 1; 

        // detailRecruit 메서드 호출
        RecruitDto recruit = mapper.detailRecruit(recruitId);

        // 결과가 null이 아닌지 검증
        assertNotNull(recruit);

        // 조회된 공고 정보 로그 출력
        log.info("조회된 공고: " + recruit);

        // 추가 검증 (예: 조회된 공고의 ID가 요청한 ID와 일치하는지)
        assertEquals(recruitId, recruit.getRecruitId());
    }
}
