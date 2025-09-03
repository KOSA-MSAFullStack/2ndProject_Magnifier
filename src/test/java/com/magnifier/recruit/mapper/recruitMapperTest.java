// RecruitMapperTest.java
// RecruitMapper 인터페이스의 DB 연동 / MyBatis 연동 기능 테스트 (CRUD 메소드 정상 동작 검증)
// 전체 채용 공고 조회 기능 테스트

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
public class RecruitMapperTest {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testGetList() throws SQLException {
        // forEach와 람다 표현식 사용, 목록 순회하며 각 공고 정보 로그로 출력
        mapper.getRecruitList().forEach(board -> log.info(board));
    }
}