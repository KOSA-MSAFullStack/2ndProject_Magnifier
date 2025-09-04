package com.magnifier.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public MemberDto read(String loginId); // 로그인id로 개인회원 조회
}
