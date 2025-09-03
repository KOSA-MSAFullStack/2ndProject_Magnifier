// RecruitMapper.java
// Mapper(DAO), DB 접근 담당 (SQL 실행)

package com.magnifier.recruit.mapper;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.magnifier.recruit.dto.RecruitDto;

@Mapper
public interface RecruitMapper {
    // 채용 공고 등록 (C, Insert) - 기업회원
    int insertRecruit(RecruitDto recruitDto) throws SQLException;
    
    // 전체 채용공고 목록 조회 (R, Select) - 개인/기업 회원
    List<RecruitDto> getRecruitList() throws SQLException;
    
    // 등록한 채용공고 목록 조회 (R, Select) - 기업회원
    List<RecruitDto> getRecruitListById(int enterpriseId) throws SQLException;

    // 공고 상세 조회 (R, Select) - 개인/기업 회원
    RecruitDto detailRecruit(int recruitId) throws SQLException;

    // 공고 수정 (U, Update) - 기업회원
    int updateRecruit(RecruitDto recruitDto) throws SQLException;

    // 공고 삭제 (D, Delete) - 기업회원
    int deleteRecruit(RecruitDto recruitDto) throws SQLException;
}
