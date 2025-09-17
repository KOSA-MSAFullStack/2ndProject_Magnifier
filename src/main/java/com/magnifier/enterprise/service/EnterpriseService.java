package com.magnifier.enterprise.service;

import com.magnifier.enterprise.dto.CreateEnterpriseRequest;
import com.magnifier.enterprise.dto.FindEnterpriseResponse;
import com.magnifier.enterprise.dto.UpdateEnterpriseRequest;
import com.magnifier.security.domain.CustomEnterprise;

/**
 * 
 * @author 김경아
 *
 */
public interface EnterpriseService {
	public void save(CreateEnterpriseRequest dto); // 회원 등록(회원가입)
	
	public FindEnterpriseResponse findMember(int enterpriseId); // 회원 정보 조회

	public void update(UpdateEnterpriseRequest dto, CustomEnterprise enterprise); // 회원정보 수정
}
