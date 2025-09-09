package com.magnifier.resume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("members/resumes")
@Log4j
public class ResumeController {

	@Autowired
    private ResumeService service;
	
	@GetMapping("/")
    public String resume(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int memberId = 0;
		if (authentication != null && authentication.isAuthenticated()) {
		    Object principal = authentication.getPrincipal();

		    if (principal instanceof CustomMember) {
		        CustomMember user = (CustomMember) principal;
		        // 사용자 도메인 정보 접근 가능
		        memberId = user.getMember().getMemberId();
		    }
		}

        // Service 계층으로 이력서 존재 여부 확인 요청
		boolean hasResume = service.hasResume(memberId);
        //boolean hasResume = service.hasResume(user.getMember().getMemberId()); 
        
        // hasResume 변수를 View로 전달
        model.addAttribute("hasResume", hasResume);
        
        return "resumes/resume"; // resume.jsp 반환
    }
	
	// 이력서 등록 폼을 보여주는 페이지
    @GetMapping("/create")
    public String showCreateForm() {
        return "resumes/create";
    }
    
    @PostMapping
    public ResponseEntity<String> register(@RequestBody ResumeDto resumeDto) {
        try {
            service.registerResume(resumeDto);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            log.error("이력서 등록 실패: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이력서 상세 정보를 보여주는 페이지
    @GetMapping("/view")
    public String viewResumePage(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int memberId = 0;
		if (authentication != null && authentication.isAuthenticated()) {
		    Object principal = authentication.getPrincipal();

		    if (principal instanceof CustomMember) {
		    	CustomMember user = (CustomMember) principal;
		        // 사용자 도메인 정보 접근 가능
		        memberId = user.getMember().getMemberId();
		    }
		}

		 // 1. 서비스에서 DTO 목록을 가져옵니다.
        ResumeDto resumes = service.findResumesByMemberId(memberId);
        
        // 2. 'resumes'라는 이름으로 DTO 목록을 Model에 담습니다.
        //    이 이름이 JSP에서 데이터를 꺼내 쓸 때 사용됩니다.
        model.addAttribute("resumes", resumes);
        System.out.println(resumes);
        return "resumes/view";
    }
	
//	@GetMapping("/view/{memberId}")
//    public ResponseEntity<List<ResumeDto>> findResumesByMemberId(@PathVariable("memberId") int memberId) {
//        log.info("findResumesByMemberId 호출: " + memberId);
//        
//        try {
//            ResumeDto resumes = service.findResumesByMemberId(memberId);
//            
//            if (resumes.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
//            }
//            return new ResponseEntity<>(resumes, HttpStatus.OK); // 200 OK
//            
//        } catch (Exception e) {
//            log.error("이력서 조회 실패: " + e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
//        }
//    }
//	
	@PutMapping("/{memberId}")
	public ResponseEntity<String> update(@PathVariable("memberId") int memberId, @RequestBody ResumeDto dto) {
	    try {
	        dto.setMemberId(memberId);
	        service.modifyResume(dto);
	        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("이력서 수정 실패: " + e.getMessage());
	        e.printStackTrace();
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}