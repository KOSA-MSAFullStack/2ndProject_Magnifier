package com.magnifier.member.service;

import com.magnifier.member.dto.CheckIdRequest;
import com.magnifier.member.dto.CreateMemberRequest;

public interface MemberService {
	public void save(CreateMemberRequest dto); // 회원 등록(회원가입)
	
	public Boolean idCheck(CheckIdRequest dto); // 아이디 중복확인
}
