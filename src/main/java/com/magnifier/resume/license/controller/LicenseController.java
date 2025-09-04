package com.magnifier.resume.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// 자격사항 추가
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
	
	// 자격사항 수정
	@PutMapping("/{licenseId}")
	public ResponseEntity<String> update(@PathVariable("licenseId") int licenseId, @RequestBody LicenseDto dto) {
	    try {
	        dto.setLicenseId(licenseId);
	        service.modifyLicense(dto);
	        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("자격사항 수정 실패: " + e.getMessage());
	        e.printStackTrace();
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// 자격사항 삭제
	@DeleteMapping("/{licenseId}")
	public ResponseEntity<String> delete(@PathVariable("licenseId") int licenseId) {
	    try {
	        service.deleteLicense(licenseId);
	        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("자격사항 삭제 실패: " + e.getMessage());
	        e.printStackTrace();
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
