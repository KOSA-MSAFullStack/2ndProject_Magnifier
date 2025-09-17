package com.magnifier.applylist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;

/**
 * 
 * @author 이상우
 *
 */
@Mapper
public interface ApplylistMapper {

	// 회원 지원내역 조회
	public List<MemberApplylistDto> selectApplylistsByMemberId(int memberId);
	
	// 기업 지원내역 조회
	public List<EnterpriseApplylistDto> selectApplylistsByEnterpriseId(int enterpriseId);
	
	// 해당 채용공고에 이미 지원했는지 확인하는 로직
	public int checkDuplicateApply(@Param("memberId") int memberId, @Param("recruitId") int recruitId);
}
