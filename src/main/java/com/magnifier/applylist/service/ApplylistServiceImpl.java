// ApplylistServiceImpl.java
// 지원 목록 서비스 구현

package com.magnifier.applylist.service;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;
import com.magnifier.applylist.entity.Applylist;
import com.magnifier.applylist.mapper.ApplylistInsertMapper;
import com.magnifier.applylist.mapper.ApplylistMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ApplylistServiceImpl implements ApplylistService {

	@Autowired
	private ApplylistInsertMapper applylistInsertMapper;

	@Autowired
	private ApplylistMapper applylistMapper;

	// * author: 김기성 
	// 지원 정보 추가
	@Override
	public void insertApplylist(Applylist applylist) {
		// 이미 지원한 공고인지 확인
		// if (조건문) 주석: 이미 지원한 공고인지 확인
		if (applylistInsertMapper.selectApplylistByMemberAndRecruit(applylist.getMemberId(), applylist.getRecruitId()) != null) {
			throw new IllegalStateException("이미 지원한 공고입니다.");
		}

		// 지원 일자 설정
		applylist.setCreatedAt(new Date()); // 현재 날짜 설정

		applylistInsertMapper.insertApplylist(applylist);
	}

	// * author: 이상우
	@Override
	public List<MemberApplylistDto> selectApplylistsByMemberId(int memberId) {
		return applylistMapper.selectApplylistsByMemberId(memberId);
	}

	// * author: 이상우
	@Override
	public List<EnterpriseApplylistDto> selectApplylistsByEnterpriseId(int enterpriseId) {
		return applylistMapper.selectApplylistsByEnterpriseId(enterpriseId);
	}
}
