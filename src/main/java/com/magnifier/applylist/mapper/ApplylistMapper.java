package com.magnifier.applylist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.applylist.dto.MemberApplylistDto;

@Mapper
public interface ApplylistMapper {

	// 회원 지원내역 조회
	public List<MemberApplylistDto> selectApplylistsByMemberId(int memberId);
}
