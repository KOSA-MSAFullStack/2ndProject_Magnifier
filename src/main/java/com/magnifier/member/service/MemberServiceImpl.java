package com.magnifier.member.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.magnifier.member.dto.CreateMemberRequest;
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
	public void save(CreateMemberRequest dto) {
		// 생년월일 조합
		LocalDate birth = LocalDate.of(
				dto.getYear(), 
				dto.getMonth(), 
				dto.getDay()
		);
		
		// 비밀번호 암호화
		String encodePW = passwordEncoder.encode(dto.getPassword()); 
		
		Member member = Member.createMember(dto, encodePW, birth); // Member 객체 생성
		memberMapper.save(member); 
	}
	
	
}
