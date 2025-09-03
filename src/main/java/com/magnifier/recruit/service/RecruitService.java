// RecruitService.java
// Service 인터페이스, 비즈니스 로직 처리 담당 (서비스 기능 선언, 실제 구현 x)

package com.magnifier.recruit.service;

import java.sql.SQLException;
import java.util.List;
import com.magnifier.recruit.dto.RecruitDto;

public interface RecruitService {
    // 채용공고 전체 목록 조회
    List<RecruitDto> getRecruitList() throws SQLException;

    // 채용 공고 등록 (C, Insert)
    int insertRecruit(RecruitDto recruitDto) throws SQLException;

    // 상세보기 (R, Select)
    RecruitDto detailRecruit(int recruitId) throws SQLException;

    // 공고 수정 (U, Update)
    int updateRecruit(RecruitDto recruitDto) throws SQLException;

    // 공고 삭제 (D, Delete)
    int deleteRecruit(int recruitId) throws SQLException;
}
