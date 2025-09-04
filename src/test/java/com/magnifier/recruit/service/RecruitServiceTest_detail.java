// RecruitServiceTest_detail.java
// RecruitService 채용 공고 상세 조회 기능 테스트
/*
 * 설명:
 * - RecruitService의 detailRecruit 메서드 정상 동작 확인 테스트
 * - 서비스 계층의 비즈니스 로직 독립 테스트
 *
 * 주요 기능:
 * - 공고 상세 조회 성공 시, DTO 반환 검증
 * - 공고 x 경우, RuntimeException 발생 검증
 */

package com.magnifier.recruit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.mapper.RecruitMapper;
import lombok.extern.log4j.Log4j;

@RunWith(MockitoJUnitRunner.class)
@Log4j
public class RecruitServiceTest_detail {

    @Mock
    private RecruitMapper recruitMapper;

    @InjectMocks
    private RecruitServiceImpl recruitService;

    @Before
    public void setUp() {
        // Mock 객체 초기화
    }

    // 채용 공고 상세 조회 성공 테스트
    @Test
    public void testDetailRecruit_Success() throws SQLException {
        // given: Mock 객체 동작 정의
        int recruitId = 1;
        RecruitDto expectedDto = new RecruitDto();
        expectedDto.setRecruitId(recruitId);
        expectedDto.setTitle("테스트 공고");

        // when: recruitMapper.detailRecruit 호출 시 expectedDto 반환하도록 설정
        when(recruitMapper.detailRecruit(recruitId)).thenReturn(expectedDto);

        // when: 서비스 메서드 호출
        RecruitDto resultDto = recruitService.detailRecruit(recruitId);

        // then: 결과 검증
        assertNotNull(resultDto);
        assertEquals(recruitId, resultDto.getRecruitId());
        assertEquals("테스트 공고", resultDto.getTitle());
        log.info("상세 조회 테스트 성공: " + resultDto);
    }

    // 채용 공고 상세 조회 실패 테스트 (공고 없음)
    @Test
    public void testDetailRecruit_NotFound() throws SQLException {
        // given: Mock 객체 동작 정의 (null 반환)
        int recruitId = 999; // 존재하지 않는 ID 가정
        when(recruitMapper.detailRecruit(recruitId)).thenReturn(null);

        // when, then: RuntimeException 발생 검증
        // assertThrows를 사용하여 특정 예외가 발생하는지 확인
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recruitService.detailRecruit(recruitId);
        });

        // 예외 메시지 검증
        assertEquals("조회된 공고가 없습니다. (공고 ID: " + recruitId + ")", exception.getMessage());
        log.info("상세 조회(실패) 테스트 성공: " + exception.getMessage());
    }
}
