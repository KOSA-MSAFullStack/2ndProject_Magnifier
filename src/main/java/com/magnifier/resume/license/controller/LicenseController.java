package com.magnifier.resume.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magnifier.resume.license.dto.LicenseDto;
import com.magnifier.resume.license.service.LicenseService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/resumes/licenses")
@Log4j
public class LicenseController {

	@Autowired
	private LicenseService service;
	
	@PostMapping
	public ResponseEntity<String> register(@RequestBody LicenseDto dto) {
        try {
            service.insertLicense(dto);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            log.error("자격사항 등록 실패: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
