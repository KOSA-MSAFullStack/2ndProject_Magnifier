package com.magnifier.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.entity.Member;

@Mapper
public interface MemberMapper {
	public Member read(String loginId); // 로그인id로 개인회원 조회
	
	public void save(Member member); // 회원 가입
}
