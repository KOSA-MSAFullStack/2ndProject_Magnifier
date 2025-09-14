package com.magnifier.applylist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;
import com.magnifier.applylist.service.ApplylistService;
import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.service.ResumeService;
import com.magnifier.security.domain.CustomEnterprise;
import com.magnifier.security.domain.CustomMember;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ApplylistController {
	
	@Autowired
	private ApplylistService applylistService;
	
	@Autowired
	private ResumeService resumeService;
	
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
		System.out.println("어떻게 오니?" + applylist.toString());
        model.addAttribute("applylist", applylist);
        return "enterprises/applylist";
    }
	
	/**
	 * 공고에 지원한 이력서 상세 정보를 보여주는 페이지	 
	 * @param model
	 * @param authentication
	 * @return
	 */
    @GetMapping("/enterprises/resume/{memberId}")
    public String viewResumePage(Model model, @PathVariable("memberId") int memberId) {
		// 1. 서비스에서 DTO 목록을 가져옵니다.
        ResumeDto resumes = resumeService.findResumesByMemberId(memberId);
        
        // 2. 'resumes'라는 이름으로 DTO 목록을 Model에 담습니다.
        //    이 이름이 JSP에서 데이터를 꺼내 쓸 때 사용됩니다.
        model.addAttribute("resumes", resumes);
        return "enterprises/memberResume";
    }
}
