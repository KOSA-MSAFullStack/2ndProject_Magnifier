package com.magnifier.enterprise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magnifier.enterprise.dto.CreateEnterpriseRequest;
import com.magnifier.enterprise.dto.FindEnterpriseResponse;
import com.magnifier.enterprise.dto.UpdateEnterpriseRequest;
import com.magnifier.enterprise.service.EnterpriseService;
import com.magnifier.security.domain.CustomEnterprise;

import lombok.extern.log4j.Log4j;
 
/**
 * 데이터 요청을 위한 api 컨트롤러
 * @author 김경아
 *
 */
@Log4j
@RestController
@RequestMapping("/enterprises/api") // 스프링 시큐리티 권한 체크를 위한 분기처리
public class EnterpriseApiController {
	
	private final EnterpriseService enterpriseService; // 서비스 의존성 주입
	
	public EnterpriseApiController(EnterpriseService enterpriseService) { // 생성자 주입
		this.enterpriseService = enterpriseService;
	}
	
	/**
	 * 회원가입 요청
	 * @param dto
	 * @return 회원가입 시 로그인 페이지로 이동
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody CreateEnterpriseRequest dto) {
		log.info(dto);
		enterpriseService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * 기업 회원 정보 조회 요청 api
	 * @return dto(FindEnterpriseResponse)
	 */
	@GetMapping("/mypage")
	public ResponseEntity<FindEnterpriseResponse> findEnterprise(Authentication authentication) {
		// 로그인한 회원 정보
		CustomEnterprise enterprise = (CustomEnterprise) authentication.getPrincipal();
		int enterpriseId = enterprise.getEnterpriseId();

		// 비즈니스 로직 서비스에서 처리
		FindEnterpriseResponse findEnterprise = enterpriseService.findMember(enterpriseId); 
		
		return ResponseEntity.ok().body(findEnterprise);
	}
	
	/**
	 * 회원 정보 수정 요청 api
	 * @param dto
	 * @return http 응답 코드
	 */
	@PutMapping("/mypage")
	public ResponseEntity<String> updateMember(@RequestBody UpdateEnterpriseRequest dto, Authentication authentication) {
		// 로그인한 회원 정보
		CustomEnterprise enterprise = (CustomEnterprise) authentication.getPrincipal();
		
		// 비즈니스 로직 서비스에서 처리
		enterpriseService.update(dto, enterprise); 
		return ResponseEntity.ok().build();
	}
}
