// RecruitMapperTest_list.java
// RecruitMapper 인터페이스의 DB 연동 / MyBatis 연동 기능 테스트 (CRUD 메소드 정상 동작 검증)
// 전체 채용공고 목록 조회 (R, Select) - 개인/기업 회원

package com.magnifier.recruit.mapper;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import com.magnifier.recruit.dto.RecruitDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RecruitMapperTest_list {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testGetList() throws SQLException {
        // DB에서 전체 리스트 조회
        List<RecruitDto> list = mapper.getRecruitList();

        // 리스트가 null이 아닌지 검증
        assertNotNull(list);

        // forEach와 람다 표현식 사용, 목록 순회하며 각 공고 정보 로그로 출력
        list.forEach(board -> log.info(board));

        log.info("----------");
        log.info("전체 공고 수: " + list.size());
    }
}