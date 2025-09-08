package com.magnifier.member.service;

import com.magnifier.member.dto.CreateMemberRequest;

public interface MemberService {
	public void save(CreateMemberRequest dto); // 회원 등록(회원가입)
}
