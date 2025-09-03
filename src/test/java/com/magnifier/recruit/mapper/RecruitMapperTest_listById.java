// RecruitMapperTest_listById.java
// 역할: RecruitMapper.getRecruitListById() 메서드 테스트
/*
 * 설명 및 주요 기능
 * - 특정 기업회원이 등록한 채용공고 목록 조회 기능 검증
 * - Spring-test, JUnit, Log4j 라이브러리 활용
 * - MyBatis 매퍼 인터페이스와 XML 쿼리 연동 테스트
 */

package com.magnifier.recruit.mapper;

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
public class RecruitMapperTest_listById {
    @Autowired
    private RecruitMapper mapper;

    @Test
    public void testGetListById() throws SQLException {
        // enterpriseId가 1인 기업의 채용 공고 목록 조회
        int enterpriseId = 1;
        List<RecruitDto> list = mapper.getRecruitListById(enterpriseId);

        // 반환된 리스트가 null이 아닌지 검증
        assertNotNull(list);

        // forEach와 람다 표현식 사용, 목록 순회하며 각 공고 정보 로그로 출력
        list.forEach(board -> log.info(board));

        log.info("----------");
        log.info("기업 ID " + enterpriseId + "의 전체 공고 수: " + list.size());
    }
}