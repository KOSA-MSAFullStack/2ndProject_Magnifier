// RecruitService.java
// Service 인터페이스, 비즈니스 로직 처리 담당 (서비스 기능 선언, 실제 구현 x)

package com.magnifier.recruit.service;

import java.sql.SQLException;
import java.util.List;
import com.magnifier.recruit.dto.RecruitDto;

public interface RecruitService {
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
}
