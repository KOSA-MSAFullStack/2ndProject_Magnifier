package com.magnifier.enterprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
 
/**
 *
 * @author 김경아
 *
 */
@Log4j
@Controller
@RequestMapping("/enterprise/*") // 스프링 시큐리티 권한 체크를 위한 분기처리
public class EnterpriseController {
	
	/**
	 * 로그아웃
	 */
	@GetMapping("/logout")
	public void logout() {
	    // 로그아웃 요청이 들어왔을 때 로그 기록
	    log.info("enterprise logout");
	} // end logout...
	
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
}
