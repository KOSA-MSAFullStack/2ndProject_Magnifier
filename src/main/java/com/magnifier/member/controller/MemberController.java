package com.magnifier.member.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
//@RequestMapping("/member")
public class MemberController {
	
	// 로그아웃 페이지 GET 요청 처리
	@GetMapping("customLogout")
	public void logoutGET() {
	    // 로그아웃 요청이 들어왔을 때 로그 기록
	    log.info("custom logout");
	} // end logout...
	
	// 커스텀 로그인 페이지 GET 요청 처리
	@GetMapping("/memberLogin")
	public void loginInput(String error, String logout, Model model) {
	    // 로그인 오류가 존재하는지 로그 출력
	    log.info("error" + error);
	    // 로그아웃 파라미터가 전달되었는지 로그 출력
	    log.info("logout" + logout);

	    // 로그인 오류가 있다면, 에러 메시지를 모델에 담아 뷰에 전달
	    if(error != null) {
	        model.addAttribute("error", "Login Error check your Account");
	    } // end if

	    // 로그아웃이 성공적으로 처리된 경우, 메시지를 모델에 담아 뷰에 전달
	    if(logout != null) {
	        model.addAttribute("logout", "LoginOut!! ");
	    } // end if
	}
   
    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model) {        
        // 인증 객체(auth)를 로그로 출력. 누가 접근 거부되었는지 기록하기 위함
        log.info("access Denied : " + auth );      
        
        // 뷰단에 전달할 메시지 속성 "msg"에 "Access Denied" 문자열을 세팅
        model.addAttribute("msg", "Access Denied");    
    }//end accessDenied
}
