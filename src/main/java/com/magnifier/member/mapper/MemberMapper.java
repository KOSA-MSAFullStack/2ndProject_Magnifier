package com.magnifier.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.member.entity.Member;

@Mapper
public interface MemberMapper {
	public Member read(String loginId); // 로그인id로 개인회원 조회(스프링 시큐리티 로그인)
	
	public void save(Member member); // 회원 가입
	
	public int idCheck(String loginId); // 아이디 중복확인
	
	public Member findById(int memberId); // 내 정보에서 개인 정보 조회
	
	public int update(Member member); // 회원정보 수정
}
