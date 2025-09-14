package com.magnifier.applylist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.applylist.dto.MemberApplylistDto;
import com.magnifier.applylist.mapper.ApplylistMapper;

@Service
public class ApplylistServiceImpl implements ApplylistService {
	
	@Autowired
	private ApplylistMapper applylistMapper;

	// 회원 지원내역 조회
	@Override
	public List<MemberApplylistDto> selectApplylistsByMemberId(int memberId) {
		return applylistMapper.selectApplylistsByMemberId(memberId);
	}

	
}
