package com.magnifier.resume.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magnifier.member.dto.FindMemberResponse;
import com.magnifier.member.service.MemberService;
import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.service.ResumeService;
import com.magnifier.security.domain.CustomMember;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @author 이상우
 *
 */
@Controller
@RequestMapping("/resumes")
@Log4j
public class ResumeController {

	@Autowired
    private ResumeService resumeService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
    public String resume(Model model, Authentication authentication) {
		CustomMember member = (CustomMember) authentication.getPrincipal();
		int memberId = member.getMemberId();

        // Service 계층으로 이력서 존재 여부 확인 요청
		boolean hasResume = resumeService.hasResume(memberId);
		
        // hasResume 변수를 View로 전달
        model.addAttribute("hasResume", hasResume);
        
        return "resumes/resume"; // resume.jsp 반환
    }
	
	// 이력서 등록/수정 폼을 보여주는 페이지
    @GetMapping("/register")
    public String showCreateForm(Model model, Authentication authentication) {
    	CustomMember member = (CustomMember) authentication.getPrincipal();
		int memberId = member.getMemberId();
		
		// 회원의 기본 정보
		FindMemberResponse findMember = memberService.findMember(memberId);
		model.addAttribute("member", findMember);
		
		// 이미 이력서가 있는지 확인
        ResumeDto resumes = resumeService.findResumesByMemberId(memberId);
        if (resumes != null) {
            // 이력서가 있으면 수정 모드로 간주하고 데이터를 모델에 추가
            model.addAttribute("resumes", resumes);
        }
        return "resumes/form";
    }
    
    // 이력서 등록 요청을 처리할 PostMapping
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> register(@RequestBody ResumeDto resumeDto, Authentication authentication) {
        CustomMember member = (CustomMember) authentication.getPrincipal();
        int memberId = member.getMemberId();
        
        resumeDto.setMemberId(memberId);
        resumeService.registerResume(resumeDto); // 새로운 이력서 등록
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "이력서가 성공적으로 등록되었습니다.");
        
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created 응답
    }
    
    // 이력서 수정 요청을 처리하는 PutMapping (기존 이력서 업데이트)
    @PutMapping("/{resumeId}") // URL 경로에 이력서 ID를 포함
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(@PathVariable("resumeId") int resumeId, @RequestBody ResumeDto resumeDto, Authentication authentication) {
        CustomMember member = (CustomMember) authentication.getPrincipal();
        int memberId = member.getMemberId();
        
        resumeDto.setResumeId(resumeId);
        resumeDto.setMemberId(memberId);
        resumeService.modifyResume(resumeDto); // 기존 이력서 수정
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "이력서가 성공적으로 수정되었습니다.");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 이력서 상세 정보를 보여주는 페이지
    @GetMapping("/view")
    public String viewResumePage(Model model, Authentication authentication) {
    	CustomMember member = (CustomMember) authentication.getPrincipal();
		int memberId = member.getMemberId();

		// 1. 서비스에서 DTO 목록을 가져옵니다.
        ResumeDto resumes = resumeService.findResumesByMemberId(memberId);
        
        // 2. 'resumes'라는 이름으로 DTO 목록을 Model에 담습니다.
        //    이 이름이 JSP에서 데이터를 꺼내 쓸 때 사용됩니다.
        model.addAttribute("resumes", resumes);
        return "resumes/view";
    }

}