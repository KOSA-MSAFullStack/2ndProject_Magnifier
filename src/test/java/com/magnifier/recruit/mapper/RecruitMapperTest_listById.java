// RecruitMapperTest_listById.java
// 역할: RecruitMapper.getRecruitListById() 메서드 테스트
/*
 * 설명 및 주요 기능
 * - 특정 기업회원이 등록한 채용공고 목록 조회 기능 검증
 * - Spring-test, JUnit, Log4j 라이브러리 활용
 * - MyBatis 매퍼 인터페이스와 XML 쿼리 연동 테스트
 */

package com.magnifier.recruit.mapper;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RecruitMapperTest_listById {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testGetListById() throws SQLException {
        // enterpriseId가 1인 기업의 채용 공고 목록 조회
        int enterpriseId = 1;
        mapper.getRecruitListById(enterpriseId).forEach(board -> log.info(board));
    }
}
