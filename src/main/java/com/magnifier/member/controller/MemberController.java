package com.magnifier.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.service.MemberService;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @author 김경아
 *
 */
@Log4j
@Controller
@RequestMapping("/members") // 스프링 시큐리티 권한 체크를 위한 분기처리
public class MemberController {
	
	private final MemberService memberService; // 서비스 의존성 주입
	
	public MemberController (MemberService memberService) { // 생성자 주입
		this.memberService = memberService;
	}
	
	/**
	 * 로그아웃 요청 시
	 */
	@GetMapping("/logout")
	public void logout() {
	    // 로그아웃 요청이 들어왔을 때 로그 기록
	    log.info("member logout");
	} // end logout...
	
	/**
	 * 로그인 요청 시
	 * @param error
	 * @param logout
	 * @param model
	 */
	@GetMapping("/login")
	public void login(String error, String logout, Model model) {
	    // 로그인 오류가 존재하는지 로그 출력
	    log.info("error" + error);
	    // 로그아웃 파라미터가 전달되었는지 로그 출력
	    log.info("logout" + logout);

	    // 로그인 오류가 있다면, 에러 메시지를 모델에 담아 뷰에 전달
	    if(error != null) {
	        model.addAttribute("error", "로그인 정보가 일치하지 않습니다.");
	    } // end if

	    // 로그아웃이 성공적으로 처리된 경우, 메시지를 모델에 담아 뷰에 전달
	    if(logout != null) {
	        model.addAttribute("logout", "로그아웃 되었습니다.");
	    } // end if
	}
	
	/**
	 * 회원가입 화면 반환
	 */
	@GetMapping("/signup")
	public void signupForm() {
	    log.info("회원가입페이지");
	}
	
	
	/**
	 * 회원가입 요청
	 * @param CreateMemberRequest
	 * @param model
	 * @return 회원가입 시 로그인 페이지로 이동
	 */
	@PostMapping("/signup")
	@ResponseBody // json 데이터를 받기 위함
	public ResponseEntity<String> signup(@RequestBody CreateMemberRequest dto) {
		log.info(dto);
		memberService.save(dto); // 비즈니스 로직 서비스에서 처리
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}	
}
