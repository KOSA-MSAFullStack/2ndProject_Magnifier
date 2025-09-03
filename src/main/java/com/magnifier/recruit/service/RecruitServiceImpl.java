// RecruitServiceImpl.java
// Service 구현체, 비즈니스 로직 처리 담당 (여러 DAO/Mapper 호출해서 하나의 기능 실제 구현 o) + 트랜잭션 처리

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
        try {
            // DB에서 insert
            int result = recruitMapper.insertRecruit(recruitDto);
            log.info("공고 등록 성공", recruitDto);
            return result;
        } catch (Exception e) {    
            log.error("\n\n !!!예외 발생: {}", e.getMessage(), e);
            throw e;
        }
    }

    // 전체 채용공고 목록 조회 (R, Select) - 개인/기업 회원
    @Override
    public List<RecruitDto> getRecruitList() throws SQLException {
        try {
            // DB에서 리스트 반환 작업
            return recruitMapper.getRecruitList();
        } catch (Exception e) {
            log.error("\n\n !!!예외 발생: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    // 등록한 채용공고 목록 조회 (R, Select) - 기업회원
    @Override
    public List<RecruitDto> getRecruitListById(int enterpriseId) throws SQLException {
        try {
            return recruitMapper.getRecruitListById(enterpriseId);
        } catch (Exception e) {
            log.error("\n\n !!!예외 발생: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    // 공고 상세 조회 (R, Select) - 개인/기업 회원
    @Override
    public RecruitDto detailRecruit(int recruitId) throws SQLException {
        try {
            // DB에서 recruitId로 검색
            RecruitDto recruitDto = recruitMapper.detailRecruit(recruitId);
            // 공고를 못찾으면
            if (recruitDto == null) {
                throw new RuntimeException("공고가 존재하지 않거나 접근 권한이 없습니다.");
            }
            // 공고 찾으면 해당공고 반환
            return recruitDto;
        } catch (Exception e) {
            log.error("\n\n !!!예외 발생: {}", e.getMessage(), e);
            throw e;
        }
    }
}
