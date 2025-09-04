package com.magnifier.resume.career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.career.service.CareerService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/resumes/careers")
@Log4j
public class CareerController {

	@Autowired
	private CareerService service;
	
	@PostMapping
	public ResponseEntity<String> register(@RequestBody CareerDto dto) {
        try {
            service.insertCareer(dto);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            log.error("경력사항 등록 실패: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
