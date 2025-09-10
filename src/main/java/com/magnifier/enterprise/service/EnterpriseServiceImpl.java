package com.magnifier.enterprise.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.magnifier.enterprise.dto.CreateEnterpriseRequest;
import com.magnifier.enterprise.entity.Enterprise;
import com.magnifier.enterprise.mapper.EnterpriseMapper;

/**
 * 비즈니스 로직 수행
 * @author 김경아
 *
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
	
	private final EnterpriseMapper enterpriseMapper; // 매퍼 의존성 주입
	private final PasswordEncoder passwordEncoder;   // 암호화 의존성 주입 
	
	public EnterpriseServiceImpl(EnterpriseMapper enterpriseMapper, PasswordEncoder passwordEncoder) { // 생성자 주입
		this.enterpriseMapper = enterpriseMapper;       
		this.passwordEncoder = passwordEncoder; 
	}

	/**
	 * 회원 등록(회원가입)
	 * @param dto(CreateEnterpriseRequest)
	 */
	@Override
	public void save(CreateEnterpriseRequest dto) {
		// 비밀번호 암호화
		String encodePW = passwordEncoder.encode(dto.getPassword());
		
		// Member 객체 생성
		Enterprise enterprise = Enterprise.createEnterprise(dto, encodePW);
		
		// 회원 가입 쿼리 실행
		enterpriseMapper.save(enterprise); 
	}

}
