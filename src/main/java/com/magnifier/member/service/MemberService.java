package com.magnifier.member.service;

import com.magnifier.member.dto.CheckIdRequest;
import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.dto.FindMemberResponse;

/**
 * 
 * @author 김경아
 *
 */
public interface MemberService {
	public void save(CreateMemberRequest dto); // 회원 등록(회원가입)
	
	public Boolean idCheck(CheckIdRequest dto); // 아이디 중복확인
	
	public FindMemberResponse findMember(int memberId); // 개인 회원 정보 조회
}
