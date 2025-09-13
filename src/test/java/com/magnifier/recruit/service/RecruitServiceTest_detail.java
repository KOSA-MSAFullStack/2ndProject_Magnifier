// RecruitServiceTest_detail.java
// RecruitService 채용 공고 상세 조회 기능 테스트
/*
 * 설명:
 * - RecruitService의 detailRecruit 메서드 정상 동작 확인 테스트
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
import java.util.Map; // Map import 추가
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import lombok.extern.log4j.Log4j;

import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.mapper.RecruitMapper;
import com.magnifier.enterprise.mapper.EnterpriseMapper; // EnterpriseMapper import 추가
import com.magnifier.enterprise.entity.Enterprise; // Enterprise entity import 추가

@RunWith(MockitoJUnitRunner.class)
@Log4j
public class RecruitServiceTest_detail {

    @Mock
    private RecruitMapper recruitMapper;

    @Mock
    private EnterpriseMapper enterpriseMapper; // EnterpriseMapper Mock 추가

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
        int enterpriseId = 100; // 가상의 기업 ID
        RecruitDto expectedRecruitDto = new RecruitDto();
        expectedRecruitDto.setRecruitId(recruitId);
        expectedRecruitDto.setTitle("테스트 공고");
        expectedRecruitDto.setEnterpriseId(enterpriseId);

        Enterprise expectedEnterprise = new Enterprise();
        expectedEnterprise.setEnterpriseId(enterpriseId);
        expectedEnterprise.setName("테스트 기업");
        expectedEnterprise.setAddress("테스트 주소");

        // when: recruitMapper.detailRecruit 호출 시 expectedRecruitDto 반환하도록 설정
        when(recruitMapper.detailRecruit(recruitId)).thenReturn(expectedRecruitDto);
        // when: enterpriseMapper.findById 호출 시 expectedEnterprise 반환하도록 설정
        when(enterpriseMapper.findById(enterpriseId)).thenReturn(expectedEnterprise);

        // when: 서비스 메서드 호출
        Map<String, Object> result = recruitService.detailRecruit(recruitId);

        // then: 결과 검증
        assertNotNull(result);
        // Map에서 RecruitDto와 Enterprise 객체 추출
        RecruitDto actualRecruitDto = (RecruitDto) result.get("recruit");
        Enterprise actualEnterprise = (Enterprise) result.get("enterprise");

        assertNotNull(actualRecruitDto);
        assertEquals(recruitId, actualRecruitDto.getRecruitId());
        assertEquals("테스트 공고", actualRecruitDto.getTitle());

        assertNotNull(actualEnterprise);
        assertEquals(enterpriseId, actualEnterprise.getEnterpriseId());
        assertEquals("테스트 기업", actualEnterprise.getName());
        assertEquals("테스트 주소", actualEnterprise.getAddress());

        log.info("testDetailRecruit_Success 테스트 성공: " + result);
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
        log.info("testDetailRecruit_NotFound 테스트 성공: " + exception.getMessage());
    }
}
