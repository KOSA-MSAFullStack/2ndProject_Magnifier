package com.magnifier.applylist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;
import com.magnifier.applylist.service.ApplylistService;
import com.magnifier.security.domain.CustomEnterprise;
import com.magnifier.security.domain.CustomMember;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ApplylistController {
	
	@Autowired
	private ApplylistService applylistService;
	
	/**
	 * 개인 회원 - 개인이 지원한 내역 화면 반환
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/members/applylist")
    public String memberApplylist(Model model, Authentication authentication) {
		CustomMember member = (CustomMember) authentication.getPrincipal();
		int memberId = member.getMemberId();
		List<MemberApplylistDto> applylist = applylistService.selectApplylistsByMemberId(memberId);
		model.addAttribute("applylist", applylist);
        return "members/applylist";
    }
	
	/**
	 * 기업 회원 - 기업에 지원한 내역 화면 반환
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/enterprises/applylist")
    public String enterpriseApplylist(Model model, Authentication authentication) {
		CustomEnterprise enterprise = (CustomEnterprise) authentication.getPrincipal();
		int enterpriseId = enterprise.getEnterpriseId();
		List<EnterpriseApplylistDto> applylist = applylistService.selectApplylistsByEnterpriseId(enterpriseId);
        model.addAttribute("applylist", applylist);
        return "enterprises/applylist";
    }
}
