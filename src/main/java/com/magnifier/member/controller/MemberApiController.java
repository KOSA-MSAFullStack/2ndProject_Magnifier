package com.magnifier.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.dto.FindMemberResponse;
import com.magnifier.member.dto.UpdateMemberRequest;
import com.magnifier.member.service.MemberService;
import com.magnifier.security.domain.CustomMember;

import lombok.extern.log4j.Log4j;

/**
 * 데이터 요청을 위한 api 컨트롤러
 * @author 김경아
 *
 */
@Log4j
@RestController
@RequestMapping("/members/api") 
public class MemberApiController {
	
	private final MemberService memberService; // 서비스 의존성 주입
	
	public MemberApiController (MemberService memberService) { // 생성자 주입
		this.memberService = memberService;
	}
	
	/**
	 * 회원가입 요청 api
	 * @param dto
	 * @return 회원가입 시 로그인 페이지로 이동
	 */
	@PostMapping("/signup")
	@ResponseBody // json 데이터를 받기 위함
	public ResponseEntity<String> signup(@RequestBody CreateMemberRequest dto) {
		log.info(dto);
		memberService.save(dto); // 비즈니스 로직 서비스에서 처리
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}	
	
	/**
	 * 개인 회원 정보 조회 요청 api
	 * @return dto(FindMemberResponse)
	 */
	@GetMapping("/mypage")
	public ResponseEntity<FindMemberResponse> findMember(Authentication authentication) {
		// 로그인한 회원 정보
		CustomMember member = (CustomMember) authentication.getPrincipal();
		int memberId = member.getMemberId();

		// 비즈니스 로직 서비스에서 처리
		FindMemberResponse findMember = memberService.findMember(memberId); 
		
		return ResponseEntity.ok().body(findMember);
	}
	
	/**
	 * 회원 정보 수정 요청 api
	 * @param dto
	 * @return http 응답 코드
	 */
	@PutMapping("/mypage")
	public ResponseEntity<String> updateMember(@RequestBody UpdateMemberRequest dto, Authentication authentication) {
		// 로그인한 회원 정보
		CustomMember member = (CustomMember) authentication.getPrincipal();
		
		// 비즈니스 로직 서비스에서 처리
		memberService.update(dto, member); 
		return ResponseEntity.ok().build();
	}
}
