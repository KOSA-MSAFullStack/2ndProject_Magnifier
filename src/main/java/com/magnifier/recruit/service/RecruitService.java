// RecruitService.java
// 채용 공고 비즈니스 로직 처리 서비스 인터페이스
/*
 * 설명:
 * - 채용 공고 비즈니스 로직 정의 인터페이스
 * - 컨트롤러(Controller)와 데이터 접근 계층(Mapper) 사이의 중간 다리 역할
 * - 실제 구현 - RecruitServiceImpl.java
 *
 * 주요 기능:
 * - 채용 공고 등록
 * - 전체 채용 공고 목록 조회
 * - 기업별 채용 공고 목록 조회
 * - 채용 공고 상세 조회
 * - 채용 공고 수정
 * - 채용 공고 삭제
 */

package com.magnifier.recruit.service;

import java.sql.SQLException;
import java.util.List;
import com.magnifier.recruit.dto.RecruitDto;

public interface RecruitService {

    /**
     * 채용 공고 등록 비즈니스 로직 (C, Insert) - 기업회원
     * @param recruitDto 등록할 채용 공고 정보
     * @return 처리 결과 (1이면 성공)
     */
    int insertRecruit(RecruitDto recruitDto) throws SQLException;    
    
    /**
     * 전체 채용공고 목록 조회 비즈니스 로직 (R, Select) - 기업/개인 회원
     * @return 모든 채용 공고 목록
     */
    List<RecruitDto> getRecruitList() throws SQLException;
    
    /**
     * 기업이 등록한 채용공고 목록 조회 비즈니스 로직 (R, Select) - 기업회원
     * @param enterpriseId 조회할 기업 ID
     * @return 해당 기업의 채용 공고 목록
     */
    List<RecruitDto> getRecruitListById(int enterpriseId) throws SQLException;
    
    /**
     * 채용 공고 상세 조회 비즈니스 로직 (R, Select) - 기업/개인 회원
     * @param recruitId 조회할 채용 공고 ID
     * @return 채용 공고 상세 정보
     */
    RecruitDto detailRecruit(int recruitId) throws SQLException;
    
    /**
     * 채용 공고 수정 비즈니스 로직 (U, Update) - 기업회원
     * @param recruitDto 수정할 채용 공고 정보
     * @return 처리 결과 (1이면 성공)
     */
    int updateRecruit(RecruitDto recruitDto) throws SQLException;

    /**
     * 채용 공고 삭제 비즈니스 로직 (D, Delete) - 기업회원
     * @param recruitDto 삭제할 채용 공고 정보 (recruitId 필요)
     * @return 처리 결과 (1이면 성공)
     */
    int deleteRecruit(RecruitDto recruitDto) throws SQLException;
}
