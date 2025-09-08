package com.magnifier.security.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magnifier.member.dto.CheckIdRequest;
import com.magnifier.member.dto.CheckIdResponse;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @author 김경아
 *
 */
@Log4j
@Controller
public class CommonController {

	/**
	 * 로그아웃 요청 시
	 */
	@GetMapping("/logout")
	public void logout() {
	    // 로그아웃 요청이 들어왔을 때 로그 기록
	    log.info("member logout");
	} // end logout...
	
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
    
    
	@PostMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> idCheck(@RequestBody CheckIdRequest dto) {
		// 어디에 값을 넣지?
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
