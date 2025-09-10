package com.magnifier.enterprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magnifier.enterprise.service.EnterpriseService;

import lombok.extern.log4j.Log4j;
 
/**
 * 화면 반환을 위한 컨트롤러
 * @author 김경아
 *
 */
@Log4j
@Controller
@RequestMapping("/enterprises") // 스프링 시큐리티 권한 체크를 위한 분기처리
public class EnterpriseController {
	
	/**
	 * 로그인 
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
	        model.addAttribute("logout", "로그아웃 되었습니다. ");
	    } // end if
	}
	
	/**
	 * 회원가입 화면 반환
	 */
	@GetMapping("/signup")
	public void signupForm() {
	    log.info("회원가입페이지");
	}	
	
	/*
	 * 기업 정보 화면 반환
	 */
	@GetMapping("/mypage")
	public void mypage() {
		log.info("기업정보페이지");
	}
}
