// RecruitServiceTest_delete.java
// RecruitService 채용 공고 삭제 기능 테스트
/*
 * 설명:
 * - RecruitService의 deleteRecruit 메서드 정상 동작 확인 테스트 클래스
 *
 * 주요 기능:
 * - 공고 삭제 성공 시, 1 반환 검증
 * - 삭제 대상 공고가 없거나 권한이 없을 경우, RuntimeException 발생 검증
 * - 공고 삭제 중 예외 발생 시, 예외 전파 검증
 */

package com.magnifier.recruit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
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
public class RecruitServiceTest_delete {

    @Mock
    private RecruitMapper recruitMapper;

    @InjectMocks
    private RecruitServiceImpl recruitService;

    // 채용 공고 삭제 성공 테스트
    @Test
    public void testDeleteRecruit_Success() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1);
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.deleteRecruit 호출 시, 1 반환
        when(recruitMapper.deleteRecruit(recruitDto)).thenReturn(1);

        // when: 서비스 메서드 호출
        int result = recruitService.deleteRecruit(recruitDto);

        // then: 결과 검증 (1 반환 확인)
        assertEquals(1, result);
        log.info("testDeleteRecruit_Success 테스트 성공: " + result);
    }

    // 채용 공고 삭제 실패 테스트 (공고 없거나 권한 없음)
    @Test
    public void testDeleteRecruit_NotFoundOrNoPermission() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(999); // 존재하지 않는 ID 가정
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.deleteRecruit 호출 시, 0 반환
        when(recruitMapper.deleteRecruit(recruitDto)).thenReturn(0);

        // when, then: RuntimeException 발생 검증
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recruitService.deleteRecruit(recruitDto);
        });

        // 예외 메시지 검증
        assertEquals("삭제할 공고가 존재하지 않거나 삭제 권한이 없습니다. (공고 ID: " + recruitDto.getRecruitId() + ")", exception.getMessage());
        log.info("testDeleteRecruit_NotFoundOrNoPermission 테스트 성공: " + exception.getMessage());
    }

    // 채용 공고 삭제 중 예외 발생 테스트
    @Test
    public void testDeleteRecruit_Exception() throws SQLException {
        // given: 테스트용 RecruitDto 객체 생성
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1);
        recruitDto.setEnterpriseId(2001);

        // when: recruitMapper.deleteRecruit 호출 시, SQLException 발생
        when(recruitMapper.deleteRecruit(recruitDto)).thenThrow(new SQLException("DB 삭제 오류"));

        // when, then: SQLException 발생 검증
        SQLException exception = assertThrows(SQLException.class, () -> {
            recruitService.deleteRecruit(recruitDto);
        });

        // 예외 메시지 검증
        assertEquals("DB 삭제 오류", exception.getMessage());
        log.info("testDeleteRecruit_Exception 테스트 성공: " + exception.getMessage());
    }
}
