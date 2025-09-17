package com.magnifier.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magnifier.member.dto.CheckIdRequest;
import com.magnifier.member.service.MemberService;

import lombok.extern.log4j.Log4j;

/**
 * 개인회원, 기업회원 공통적으로 사용되는 컨트롤러
 * @author 김경아
 *
 */
@Log4j
@Controller
public class CommonController {
	
	private final MemberService memberService; // 서비스 의존성 주입
	
	public CommonController (MemberService memberService) { // 생성자 주입
		this.memberService = memberService;
	}
	
	/**
	 * 접근 거부되었을 때
	 * @param auth
	 * @param model
	 */
    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model) {        
        // 인증 객체(auth)를 로그로 출력. 누가 접근 거부되었는지 기록하기 위함
        log.info("access Denied : " + auth );      
        
        // 뷰단에 전달할 메시지 속성 "msg"에 "Access Denied" 문자열을 세팅
        model.addAttribute("msg", "Access Denied");    
    }//end accessDenied
    
    /**
     * 아이디 중복 확인
     * @param dto(CheckIdRequest)
     * @return
     */
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> idCheck(@RequestBody CheckIdRequest dto) {
		Boolean exist = memberService.idCheck(dto); // 로그인 Id 존재 여부
		return ResponseEntity.ok().body(exist); // 존재 여부 반환
	}
}
