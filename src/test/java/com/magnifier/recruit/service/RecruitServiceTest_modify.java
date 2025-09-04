// RecruitServiceTest_modify.java
// RecruitService 채용 공고 수정 기능 테스트
/*
 * 설명:
 * - RecruitService의 updateRecruit 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - 공고 수정 성공 시, 1 반환 검증
 * - 수정 대상 공고가 없거나 권한이 없을 경우, RuntimeException 발생 검증
 * - 공고 수정 중 예외 발생 시, 예외 전파 검증
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

@RunWith(MockitoJUnitRunner.class)
@Log4j
public class RecruitServiceTest_modify {

    @Mock
    private RecruitMapper recruitMapper;

    @InjectMocks
    private RecruitServiceImpl recruitService;

    // 채용 공고 수정 성공 테스트
    @Test
    public void testUpdateRecruit_Success() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1);
        recruitDto.setTitle("수정된 공고 제목");
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.updateRecruit 호출 시, 1 반환
        when(recruitMapper.updateRecruit(recruitDto)).thenReturn(1);

        // when: 서비스 메서드 호출
        int result = recruitService.updateRecruit(recruitDto);

        // then: 결과 검증 (1 반환 확인)
        assertEquals(1, result);
        log.info("testUpdateRecruit_Success 테스트 성공: " + result);
    }

    // 채용 공고 수정 실패 테스트 (공고 없거나 권한 없음)
    @Test
    public void testUpdateRecruit_NotFoundOrNoPermission() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(999); // 존재하지 않는 ID 가정
        recruitDto.setTitle("수정될 공고");
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.updateRecruit 호출 시, 0 반환
        when(recruitMapper.updateRecruit(recruitDto)).thenReturn(0);

        // when, then: RuntimeException 발생 검증
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recruitService.updateRecruit(recruitDto);
        });

        // 예외 메시지 검증
        assertEquals("수정할 공고가 존재하지 않거나 수정 권한이 없습니다. (공고 ID: " + recruitDto.getRecruitId() + ")", exception.getMessage());
        log.info("testUpdateRecruit_NotFoundOrNoPermission 테스트 성공: " + exception.getMessage());
    }

    // 채용 공고 수정 중 예외 발생 테스트
    @Test
    public void testUpdateRecruit_Exception() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1);
        recruitDto.setTitle("수정될 공고");
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.updateRecruit 호출 시, SQLException 발생
        when(recruitMapper.updateRecruit(recruitDto)).thenThrow(new SQLException("DB 업데이트 오류"));

        // when, then: SQLException 발생 검증
        SQLException exception = assertThrows(SQLException.class, () -> {
            recruitService.updateRecruit(recruitDto);
        });

        // 예외 메시지 검증
        assertEquals("DB 업데이트 오류", exception.getMessage());
        log.info("testUpdateRecruit_Exception 테스트 성공: " + exception.getMessage());
    }
}
