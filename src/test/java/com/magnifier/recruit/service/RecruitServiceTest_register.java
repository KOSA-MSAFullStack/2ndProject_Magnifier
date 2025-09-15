// RecruitServiceTest_register.java
// RecruitService 채용 공고 등록 기능 테스트
/*
 * 설명:
 * - RecruitService의 insertRecruit 메서드 정상 동작 확인 테스트 클래스
 *
 * 주요 기능:
 * - 공고 등록 성공 시, 1 반환 검증
 * - 공고 등록 중 예외 발생 시, 예외 전파 검증
 */

package com.magnifier.recruit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
//import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import lombok.extern.log4j.Log4j;

import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.mapper.RecruitMapper;

// * author: 김기성
@RunWith(MockitoJUnitRunner.class)
@Log4j
public class RecruitServiceTest_register {

    @Mock
    private RecruitMapper recruitMapper;

    @InjectMocks
    private RecruitServiceImpl recruitService;

    // 채용 공고 등록 성공 테스트
    @Test
    public void testInsertRecruit_Success() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setTitle("새 공고");
        recruitDto.setContent("새로운 공고 내용");
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.insertRecruit 호출 시 1 반환
        when(recruitMapper.insertRecruit(recruitDto)).thenReturn(1);

        // when: 서비스 메서드 호출
        int result = recruitService.insertRecruit(recruitDto);

        // then: 결과 검증 (1 반환 확인)
        assertEquals(1, result);
        log.info("testInsertRecruit_Success 테스트 성공: " + result);
    }

    // 채용 공고 등록 중 예외 발생 테스트
    @Test
    public void testInsertRecruit_Exception() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setTitle("예외 발생 공고");
        recruitDto.setContent("예외 발생 내용");
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.insertRecruit 호출 시 SQLException 발생하도록 설정
        when(recruitMapper.insertRecruit(recruitDto)).thenThrow(new SQLException("DB 오류 발생"));

        // when, then: SQLException 발생 검증
        SQLException exception = assertThrows(SQLException.class, () -> {
            recruitService.insertRecruit(recruitDto);
        });

        // 예외 메시지 검증
        assertEquals("DB 오류 발생", exception.getMessage());
        log.info("testInsertRecruit_Exception 테스트 성공: " + exception.getMessage());
    }
}
