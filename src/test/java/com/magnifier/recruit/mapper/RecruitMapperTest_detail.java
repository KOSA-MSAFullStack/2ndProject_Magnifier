// RecruitMapperTest_detail.java
// RecruitMapper Select(R) 테스트
// 공고 상세 보기 테스트

package com.magnifier.recruit.mapper;

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
    private RecruitMapper recruitMapper;

    @Test
    public void detailRecruit() throws SQLException {
        RecruitDto recruit = recruitMapper.detailRecruit(1);
        log.info(recruit);

    }
}
