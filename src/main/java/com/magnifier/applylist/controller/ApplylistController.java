package com.magnifier.applylist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ApplylistController {
	
	/**
	 * 개인 회원 - 개인이 지원한 내역 화면 반환
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/members/applylist")
    public String memberApplylist(Model model, Authentication authentication) {
		//CustomMember member = (CustomMember) authentication.getPrincipal();
		//int memberId = member.getMemberId();

        return "members/applylist"; // resume.jsp 반환
    }
	
	/**
	 * 기업 회원 - 기업에 지원한 내역 화면 반환
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/enterprises/applylist")
    public String enterpriseApplylist(Model model, Authentication authentication) {
		//CustomMember member = (CustomMember) authentication.getPrincipal();
		//int memberId = member.getMemberId();

        
        return "enterprises/applylist"; // resume.jsp 반환
    }
}
