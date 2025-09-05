package com.magnifier.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.magnifier.member.dto.MemberDto;
import com.magnifier.member.entity.Member;
import com.magnifier.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper; 
	private final PasswordEncoder passwordEncoder; 
	
	public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;       // 매퍼 의존성 주입
		this.passwordEncoder = passwordEncoder; // 암호화 의존성 주입
	}

	/**
	 * 회원 등록(회원가입)
	 * @param memberDto
	 */
	@Override
	public void save(MemberDto memberDto) {
		String encodePW = passwordEncoder.encode(memberDto.getPassword()); // 비밀번호 암호화
		Member member = Member.createMember(memberDto, encodePW); // Member 객체 생성
		memberMapper.save(member); 
	}
	
	
}
