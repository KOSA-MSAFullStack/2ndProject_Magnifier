package com.magnifier.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.dto.FindMemberResponse;
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
	 * @return
	 */
	@GetMapping("/mypage")
	public ResponseEntity<FindMemberResponse> findMember() {
		// 로그인한 회원 정보
		int memberId = 0; // memberId를 담을 변수
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 현재 스프링 시큐리티 컨텍스트에서 인증 정보를 가져옴
		if (authentication != null && authentication.isAuthenticated()) { // 인증 정보가 존재하고, 인증된 상태인지 확인
		    Object principal = authentication.getPrincipal(); // 인증된 사용자의 세부 정보를 담고 있는 principal 객체를 얻음
		    
		    if (principal instanceof CustomMember) { // principal이 CustomMember 타입인지 확인 (사용자 정의 UserDetails 구현체)
		        CustomMember user = (CustomMember) principal;  // CustomMember 객체로 캐스팅하여 사용자 도메인 객체 접근
		        memberId = user.getMember().getMemberId(); // CustomMember 내부에 있는 실제 회원 도메인 객체에서 memberId를 얻음
		    }
		}

		// 서비스 계층에서 로직 구현
		FindMemberResponse findMember = memberService.findMember(memberId); 
		
		return ResponseEntity.ok().body(findMember);
	}
	
}
