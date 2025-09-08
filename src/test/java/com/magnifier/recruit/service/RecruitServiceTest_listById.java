// RecruitServiceTest_listById.java
// RecruitService 기업별 채용 공고 목록 조회 기능 테스트
/*
 * 설명:
 * - RecruitService의 getRecruitListById 메서드 정상 동작 확인 테스트
 *
 * 주요 기능:
 * - 특정 기업 ID로 공고 목록 조회 성공 시, 리스트 반환 검증
 * - 특정 기업 ID로 빈 목록 반환 시, 처리 검증
 * - 공고 목록 조회 중 예외 발생 시, 예외 전파 검증
 */

package com.magnifier.recruit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class RecruitServiceTest_listById {

    @Mock
    private RecruitMapper recruitMapper;

    @InjectMocks
    private RecruitServiceImpl recruitService;

    // 기업별 채용 공고 목록 조회 성공 테스트
    @Test
    public void testGetRecruitListById_Success() throws SQLException {
        // given: 테스트용 기업 ID 및 Mock 객체 동작 정의 (데이터 포함 리스트 반환)
        int enterpriseId = 2001;
        RecruitDto recruit1 = new RecruitDto();
        recruit1.setRecruitId(1);
        recruit1.setTitle("공고1");
        recruit1.setEnterpriseId(enterpriseId);
        RecruitDto recruit2 = new RecruitDto();
        recruit2.setRecruitId(2);
        recruit2.setTitle("공고2");
        recruit2.setEnterpriseId(enterpriseId);
        List<RecruitDto> expectedList = Arrays.asList(recruit1, recruit2);

        // when: recruitMapper.getRecruitListById 호출 시, expectedList 반환
        when(recruitMapper.getRecruitListById(enterpriseId)).thenReturn(expectedList);

        // when: 서비스 메서드 호출
        List<RecruitDto> resultList = recruitService.getRecruitListById(enterpriseId);

        // then: 결과 검증
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("공고1", resultList.get(0).getTitle());
        assertEquals(enterpriseId, resultList.get(0).getEnterpriseId().intValue());
        log.info("testGetRecruitListById_Success 테스트 성공: " + resultList);
    }

    // 기업별 채용 공고 목록 조회 (빈 목록 반환) 테스트
    @Test
    public void testGetRecruitListById_EmptyList() throws SQLException {
        // given: 테스트용 기업 ID 및 Mock 객체 동작 정의 (빈 리스트 반환)
        int enterpriseId = 9999; // 공고 없는 기업 ID 가정
        List<RecruitDto> expectedList = new ArrayList<>();
        when(recruitMapper.getRecruitListById(enterpriseId)).thenReturn(expectedList);

        // when: 서비스 메서드 호출
        List<RecruitDto> resultList = recruitService.getRecruitListById(enterpriseId);

        // then: 결과 검증
        assertNotNull(resultList);
        assertTrue(resultList.isEmpty());
        log.info("testGetRecruitListById_EmptyList 테스트 성공: " + resultList);
    }

    // 기업별 채용 공고 목록 조회 중 예외 발생 테스트
    @Test
    public void testGetRecruitListById_Exception() throws SQLException {
        // given: 테스트용 기업 ID 및 Mock 객체 동작 정의 (SQLException 발생)
        int enterpriseId = 2001;
        when(recruitMapper.getRecruitListById(enterpriseId)).thenThrow(new SQLException("DB 조회 오류"));

        // when, then: SQLException 발생 검증
        SQLException exception = assertThrows(SQLException.class, () -> {
            recruitService.getRecruitListById(enterpriseId);
        });

        // 예외 메시지 검증
        assertEquals("DB 조회 오류", exception.getMessage());
        log.info("testGetRecruitListById_Exception 테스트 성공: " + exception.getMessage());
    }
}
