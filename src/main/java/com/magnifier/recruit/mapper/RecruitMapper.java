// RecruitMapper.java
// 채용 공고 DB 접근 객체 (Mapper/DAO)
/*
 * 설명:
 * - DB의 RECRUIT 테이블에 접근하는 MyBatis Mapper 인터페이스
 * - RecruitMapper.xml에 정의된 SQL 쿼리 호출
 *
 * 주요 기능:
 * - 채용 공고 등록 (insertRecruit)
 * - 전체 채용 공고 목록 조회 (getRecruitList)
 * - 기업별 채용 공고 목록 조회 (getRecruitListById)
 * - 채용 공고 상세 조회 (detailRecruit)
 * - 채용 공고 수정 (updateRecruit)
 * - 채용 공고 삭제 (deleteRecruit)
 */

package com.magnifier.recruit.mapper;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.magnifier.recruit.dto.RecruitDto;

@Mapper
public interface RecruitMapper {

    /**
     * 채용 공고 등록 (C, Insert) - 기업회원
     * @param recruitDto 등록할 채용 공고 정보
     * @return 삽입된 행의 수 (1이면 성공)
     */
    int insertRecruit(RecruitDto recruitDto) throws SQLException;
    
    /**
     * 전체 채용공고 목록 조회 (R, Select) - 기업/개인 회원
     * @return 채용 공고 DTO 리스트
     */
    List<RecruitDto> getRecruitList() throws SQLException;
    
    /**
     * 기업이 등록한 채용공고 목록 조회 (R, Select) - 기업회원
     * @param enterpriseId 기업회원 ID
     * @return 해당 기업의 채용 공고 DTO 리스트
     */
    List<RecruitDto> getRecruitListById(int enterpriseId) throws SQLException;

    /**
     * 채용 공고 상세 조회 (R, Select) - 기업/개인 회원
     * @param recruitId 조회할 채용 공고 ID
     * @return 채용 공고 상세 정보 DTO
     */
    RecruitDto detailRecruit(int recruitId) throws SQLException;

    /**
     * 채용 공고 수정 (U, Update) - 기업회원
     * @param recruitDto 수정할 채용 공고 정보
     * @return 수정된 행의 수 (1이면 성공)
     */
    int updateRecruit(RecruitDto recruitDto) throws SQLException;

    /**
     * 채용 공고 삭제 (D, Delete) - 기업회원
     * @param recruitDto 삭제할 채용 공고 정보 (recruitId 필요)
     * @return 삭제된 행의 수 (1이면 성공)
     */
    int deleteRecruit(RecruitDto recruitDto) throws SQLException;
}
