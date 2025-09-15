// RecruitServiceImpl.java
// 채용 공고 비즈니스 로직 구현 클래스
/*
 * 설명:
 * - RecruitService 인터페이스 구현 서비스 클래스
 * - DB 상호작용 및 실제 비즈니스 로직 수행
 *
 * 주요 기능:
 * - 채용 공고 등록, 조회, 수정, 삭제 등 핵심 비즈니스 로직 구현
 * - 트랜잭션 관리
 */

package com.magnifier.recruit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.magnifier.enterprise.mapper.EnterpriseMapper;
import com.magnifier.recruit.mapper.RecruitMapper;
import com.magnifier.recruit.dto.RecruitDto;

// * author: 김기성
@Slf4j
@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 채용 공고 등록 (C, Insert) - 기업회원
     * @param recruitDto 등록할 채용 공고 정보
     * @return 삽입된 행의 수 (1이면 성공)
     */
    @Override
    public int insertRecruit(RecruitDto recruitDto) throws SQLException {
        log.info("채용 공고 [등록] 서비스 실행: {}", recruitDto);
        try {
            // DB에 채용 공고 삽입
            int result = recruitMapper.insertRecruit(recruitDto);
            log.info("공고 등록 성공. 결과: {}", result);
            return result;
        } catch (Exception e) {
            log.error("채용 공고 [등록] 중 예외 발생", e);
            throw e;
        }
    }

    /**
     * 전체 채용공고 목록 조회 (R, Select) - 기업/개인 회원
     * @return 채용 공고 DTO 리스트
     */
    @Override
    public List<RecruitDto> getRecruitList(int page, int size) throws SQLException {
        log.info("[전체 채용 공고 목록 조회] 서비스 실행 (페이지: {}, 사이즈: {})", page, size);
        try {
            int startRow = (page - 1) * size + 1;
            int endRow = page * size;
            Map<String, Integer> params = new HashMap<>();
            params.put("startRow", startRow);
            params.put("endRow", endRow);
            return recruitMapper.getRecruitList(params);
        } catch (Exception e) {
            log.error("[전체 채용 공고 목록 조회] 중 예외 발생", e);
            throw e;
        }
    }
    
    /**
     * 전체 채용공고 수 조회
     * @return 전체 공고 수
     */
    @Override
    public int getCount() throws SQLException {
        log.info("[전체 공고 수 조회] 서비스 실행");
        try {
            return recruitMapper.getCount();
        } catch (Exception e) {
            log.error("[전체 공고 수 조회] 중 예외 발생", e);
            throw e;
        }
    }
    
    /**
     * 기업이 등록한 채용공고 목록 조회 (R, Select) - 기업회원
     * @param enterpriseId 기업회원 ID
     * @return 해당 기업의 채용 공고 DTO 리스트
     */
    @Override
    public List<RecruitDto> getRecruitListById(int enterpriseId) throws SQLException {
        log.info("기업이 [등록한 공고 목록 조회] 서비스 실행 (기업 ID: {})", enterpriseId);
        try {
            // 특정 기업 채용 공고 목록 조회
            return recruitMapper.getRecruitListById(enterpriseId);
        } catch (Exception e) {
            log.error("[등록한 채용 공고 목록 조회] 중 예외 발생 (기업 ID: {})", enterpriseId, e);
            throw e;
        }
    }
    
    /**
     * 채용 공고 상세 조회 (R, Select) - 기업/개인 회원
     * @param recruitId 조회할 채용 공고 ID
     * @return 채용 공고 및 기업 정보가 담긴 Map
     */
    @Override
    public Map<String, Object> detailRecruit(int recruitId) throws SQLException {
        log.info("채용 공고 [상세 조회] 서비스 실행 (공고 ID: {})", recruitId);
        try {
            // 1. 채용 공고 정보 조회
            RecruitDto recruit = recruitMapper.detailRecruit(recruitId);
            // 조회된 공고 없는 경우
            if (recruit == null) {
                throw new RuntimeException("조회된 공고가 없습니다. (공고 ID: " + recruitId + ")");
            }

            // 2. 공고 정보에서 enterpriseId를 이용해 기업 정보 조회
            Integer enterpriseId = recruit.getEnterpriseId();

            com.magnifier.enterprise.entity.Enterprise fetchedEnterprise = enterpriseMapper.findById(enterpriseId);

            if (fetchedEnterprise == null) {
                log.warn("공고에 연결된 기업 정보가 없습니다. (기업 ID: {})", enterpriseId);
                throw new RuntimeException("공고에 연결된 기업 정보가 없습니다. (기업 ID: " + enterpriseId + ")");
            }

            // 클라이언트에 필요한 정보만 담은 Enterprise 객체 생성
            com.magnifier.enterprise.entity.Enterprise enterprise = new com.magnifier.enterprise.entity.Enterprise();
            enterprise.setName(fetchedEnterprise.getName());
            enterprise.setAddress(fetchedEnterprise.getAddress());
            enterprise.setAddressDetail(fetchedEnterprise.getAddressDetail());
            log.debug("클라이언트 전달용 Enterprise 객체: {}", enterprise);

            // 3. 두 정보를 Map에 담아서 반환
            Map<String, Object> details = new HashMap<>();
            details.put("recruit", recruit);
            details.put("enterprise", enterprise);

            return details;
        } catch (Exception e) {
            log.error("채용 공고 [상세 조회] 중 예외 발생 (공고 ID: {})", recruitId, e);
            throw e;
        }
    }
    
    /**
     * 채용 공고 수정 (U, Update) - 기업회원
     * @param recruitDto 수정할 채용 공고 정보
     * @return 수정된 행의 수 (1이면 성공)
     */
    @Override
    public int updateRecruit(RecruitDto recruitDto) throws SQLException {
        log.info("채용 공고 [수정] 서비스 실행: {}", recruitDto);
        try {
            // 채용 공고 정보 수정
            int result = recruitMapper.updateRecruit(recruitDto);

            // 수정된 행 없는 경우 (공고 없거나 권한 없음)
            if (result == 0) {
                throw new RuntimeException("수정할 공고가 존재하지 않거나 수정 권한이 없습니다. (공고 ID: " + recruitDto.getRecruitId() + ")");
            }
            log.info("공고 수정 성공. 결과: {}", result);
            return result;
        } catch (Exception e) {
            log.error("채용 공고 [수정] 중 예외 발생", e);
            throw e;
        }
    }

    /**
     * 채용 공고 삭제 (D, Delete) - 기업회원
     * @param recruitDto 삭제할 채용 공고 정보 (recruitId 필요)
     * @return 삭제된 행의 수 (1이면 성공)
     */
    @Override
    public int deleteRecruit(RecruitDto recruitDto) throws SQLException {
        log.info("채용 공고 [삭제] 서비스 실행 (공고 ID: {})", recruitDto.getRecruitId());
        try {
            // 채용 공고 정보 삭제
            int result = recruitMapper.deleteRecruit(recruitDto);

            // 삭제된 행 없는 경우 (공고 없거나 권한 없음)
            if (result == 0) {
                throw new RuntimeException("삭제할 공고가 존재하지 않거나 삭제 권한이 없습니다. (공고 ID: " + recruitDto.getRecruitId() + ")");
            }
            log.info("공고 삭제 성공. 결과: {}", result);
            return result;
        } catch (Exception e) {
            log.error("채용 공고 [삭제] 중 예외 발생", e);
            throw e;
        }
    }
    /**
     * 채용 공고 일괄 삭제 비즈니스 로직 (D, Delete) - 기업회원
     * @param recruitId 삭제할 채용 공고 ID 리스트
     * @return 처리 결과 (삭제된 행의 수)
     */
    @Override
    public int deleteRecruitsById(List<Integer> recruitId) throws SQLException {
        log.info("채용 공고 [일괄 삭제] 서비스 실행 (공고 ID 리스트: {})", recruitId);
        try {
            int deletedCount = recruitMapper.deleteRecruitsById(recruitId);
            log.info("공고 일괄 삭제 성공. 삭제된 공고 수: {}", deletedCount);
            return deletedCount;
        } catch (Exception e) {
            log.error("채용 공고 [일괄 삭제] 중 예외 발생 (공고 ID 리스트: {})", recruitId, e);
            throw e;
        }
    }
}
