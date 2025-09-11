package com.magnifier.enterprise.service;

import com.magnifier.enterprise.dto.CreateEnterpriseRequest;
import com.magnifier.enterprise.dto.FindEnterpriseResponse;

public interface EnterpriseService {
	public void save(CreateEnterpriseRequest dto); // 회원 등록(회원가입)
	
	public FindEnterpriseResponse findMember(int enterpriseId); // 회원 정보 조회
}
