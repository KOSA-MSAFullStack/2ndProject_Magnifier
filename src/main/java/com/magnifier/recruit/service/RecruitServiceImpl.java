// RecruitServiceImpl.java
// Service 구현체, 비즈니스 로직 처리 담당 (여러 DAO/Mapper 호출해서 하나의 기능 실제 구현 o) + 트랜잭션 처리

package com.magnifier.recruit.service;

import java.sql.SQLException;
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

}
