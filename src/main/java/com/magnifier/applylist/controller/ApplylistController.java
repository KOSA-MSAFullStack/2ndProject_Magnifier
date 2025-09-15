// ApplylistController.java
// 지원 목록 컨트롤러

package com.magnifier.applylist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.magnifier.applylist.dto.EnterpriseApplylistDto;
import com.magnifier.applylist.dto.MemberApplylistDto;
import com.magnifier.applylist.entity.Applylist;
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

	// * author: 김기성 
	/**
	 * 채용 공고 지원
	 * 
	 * @param payload
	 * @param authentication
	 * @return
	 */
	@PostMapping("/applylist/apply/{recruitId}")
	public ResponseEntity<String> applyForRecruit(@PathVariable("recruitId") int recruitId,	Authentication authentication) {
		CustomMember member = (CustomMember) authentication.getPrincipal();
		int memberId = member.getMemberId();
		
		// 해당 채용공고에 이미 지원했는지 확인하는 로직
	    int alreadyApplied = applylistService.checkDuplicateApply(memberId, recruitId);

	    // 중복 지원 여부에 따라 다른 응답 반환
	    if (alreadyApplied > 0) {
	        return new ResponseEntity<>("이미 지원한 채용공고입니다.", HttpStatus.CONFLICT);
	    }

		Applylist applylist = new Applylist();
		applylist.setMemberId(memberId);
		applylist.setRecruitId(recruitId);
		applylistService.insertApplylist(applylist);

		return new ResponseEntity<>("지원이 완료되었습니다.", HttpStatus.OK);
	}

	// * author: 이상우
	/**
	 * 개인 회원 - 개인이 지원한 내역 화면 반환
	 * 
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

	// * author: 이상우
	/**
	 * 기업 회원 - 기업에 지원한 내역 화면 반환
	 * 
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

	// * author: 이상우
	/**
	 * 공고에 지원한 이력서 상세 정보를 보여주는 페이지
	 * 
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/enterprises/resume/{memberId}")
	public String viewResumePage(Model model, @PathVariable("memberId") int memberId) {
		// 1. 서비스에서 DTO 목록을 가져옵니다.
		ResumeDto resumes = resumeService.findResumesByMemberId(memberId);

		// 2. 'resumes'라는 이름으로 DTO 목록을 Model에 담습니다.
		// 이 이름이 JSP에서 데이터를 꺼내 쓸 때 사용됩니다.
		model.addAttribute("resumes", resumes);
		return "enterprises/memberResume";
	}
}