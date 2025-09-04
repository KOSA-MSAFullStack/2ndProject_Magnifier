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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.magnifier.recruit.mapper.RecruitMapper;
import com.magnifier.recruit.dto.RecruitDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    // 채용 공고 등록 (C, Insert) - 기업회원
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

    // 전체 채용공고 목록 조회 (R, Select) - 기업/개인 회원
    @Override
    public List<RecruitDto> getRecruitList() throws SQLException {
        log.info("[전체 채용 공고 목록 조회] 서비스 실행");
        try {
            // 전체 채용 공고 목록 조회
            return recruitMapper.getRecruitList();
        } catch (Exception e) {
            log.error("[전체 채용 공고 목록 조회] 중 예외 발생", e);
            throw e;
        }
    }
    
    // 기업이 등록한 채용공고 목록 조회 (R, Select) - 기업회원
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
    
    // 채용 공고 상세 조회 (R, Select) - 기업/개인 회원
    @Override
    public RecruitDto detailRecruit(int recruitId) throws SQLException {
        log.info("채용 공고 [상세 조회] 서비스 실행 (공고 ID: {})", recruitId);
        try {
            // 특정 채용 공고 정보 조회
            RecruitDto recruitDto = recruitMapper.detailRecruit(recruitId);
            
            // 조회된 공고 없는 경우
            if (recruitDto == null) {
                throw new RuntimeException("조회된 공고가 없습니다. (공고 ID: " + recruitId + ")");
            }
            return recruitDto;
        } catch (Exception e) {
            log.error("채용 공고 [상세 조회] 중 예외 발생 (공고 ID: {})", recruitId, e);
            throw e;
        }
    }
    
    // 채용 공고 수정 (U, Update) - 기업회원
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

    // 채용 공고 삭제 (D, Delete) - 기업회원
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
}
