// RecruitMapper.java
// Mapper(DAO), DB 접근 담당 (SQL 실행)

package com.magnifier.recruit.mapper;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.magnifier.recruit.dto.RecruitDto;

@Mapper
public interface RecruitMapper {
    // 채용 공고 등록
    void insertRecruit(RecruitDto recruitDto) throws SQLException;

    // 공고 조회
    List<RecruitDto> getRecruitList() throws SQLException;

    // 공고 수정
    int updateRecruit(RecruitDto recruitDto) throws SQLException;

    // 공고 삭제
    int deleteRecruit(RecruitDto recruitDto) throws SQLException;
}
