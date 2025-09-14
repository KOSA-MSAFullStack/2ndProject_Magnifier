package com.magnifier.applylist.service;

import java.util.List;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;

public interface ApplylistService {
	
	// 회원 지원내역 조회
	public List<MemberApplylistDto> selectApplylistsByMemberId(int memberId);
	
	// 기업 지원내역 조회
	public List<EnterpriseApplylistDto> selectApplylistsByEnterpriseId(int enterpriseId);
}
