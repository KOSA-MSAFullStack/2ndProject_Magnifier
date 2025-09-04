package com.magnifier.resume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.service.ResumeService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/resumes")
@Log4j
public class ResumeController {

	@Autowired
    private ResumeService service;  
	
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
	
	@GetMapping("/{memberId}")
    public ResponseEntity<List<ResumeDto>> findResumesByMemberId(@PathVariable("memberId") int memberId) {
        log.info("findResumesByMemberId 호출: " + memberId);
        
        try {
            List<ResumeDto> resumes = service.findResumesByMemberId(memberId);
            
            if (resumes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(resumes, HttpStatus.OK); // 200 OK
            
        } catch (Exception e) {
            log.error("이력서 조회 실패: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

}