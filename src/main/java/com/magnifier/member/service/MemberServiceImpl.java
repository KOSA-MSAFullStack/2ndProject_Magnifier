package com.magnifier.member.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.magnifier.member.dto.CheckIdRequest;
import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.dto.FindMemberResponse;
import com.magnifier.member.entity.Member;
import com.magnifier.member.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

/**
 * 비즈니스 로직 수행
 * @author 김경아
 *
 */
@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;       // 매퍼 의존성 주입
	private final PasswordEncoder passwordEncoder; // 암호화 의존성 주입 
	
	public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) { // 생성자 주입
		this.memberMapper = memberMapper;       
		this.passwordEncoder = passwordEncoder; 
	}

	/**
	 * 회원 등록(회원가입)
	 * @param dto(CreateMemberRequest)
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
		
		// Member 객체 생성
		Member member = Member.createMember(dto, encodePW, birth); 
		
		// 회원 가입 쿼리 실행
		memberMapper.save(member); 
	}

	/**
	 * 아이디 중복 확인
	 * @param dto(CheckIdRequest)
	 */
	@Override
	public Boolean idCheck(CheckIdRequest dto) {
		Boolean exist = false; // 로그인 Id 존재 여부
		
		String loginId = dto.getLoginId(); // 로그인 Id
		
		int result = memberMapper.idCheck(loginId); // 로그인 Id 중복 확인
		
		if (result == 1) { // 존재한다면
			exist = true;  // 존재여부 : true
		}
		
		return exist;
	}

	/**
	 * 개인 회원 정보 조회
	 * @param memberId
	 */
	@Override
	public FindMemberResponse findMember(int memberId) {
		// Id로 개인 회원 정보 조회
		Member member = memberMapper.findById(memberId);	
		
		// dto 생성
		FindMemberResponse findMember = FindMemberResponse.createFindMemberResponse(member);
		
		return findMember;
	}
	
	
}
