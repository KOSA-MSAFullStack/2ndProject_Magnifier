// ApplylistService.java
// 지원 목록 서비스 인터페이스

package com.magnifier.applylist.service;

import java.util.List;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;
import com.magnifier.applylist.entity.Applylist;

public interface ApplylistService {
	// * author: 김기성 
	// 지원 정보 추가
	public void insertApplylist(Applylist applylist);
	
	// * author: 이상우
	// 개인 회원 - 개인이 지원한 내역 조회
	public List<MemberApplylistDto> selectApplylistsByMemberId(int memberId);
	
	// * author: 이상우
	// 기업 회원 - 기업에 지원한 내역 조회
	public List<EnterpriseApplylistDto> selectApplylistsByEnterpriseId(int enterpriseId);
}
