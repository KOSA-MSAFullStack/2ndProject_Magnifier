// RecruitMapperTest_delete.java
// RecruitMapper Delete(D) 테스트
// 공고 삭제 테스트

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
public class RecruitMapperTest_delete {
    @Autowired
    private RecruitMapper recruitMapper;

    @Test
    public void deleteRecruit() throws SQLException {
        RecruitDto recruit = new RecruitDto();
        recruit.setRecruitId(2); 
        log.info("Delete count:" + recruitMapper.deleteRecruit(2));

    }
}