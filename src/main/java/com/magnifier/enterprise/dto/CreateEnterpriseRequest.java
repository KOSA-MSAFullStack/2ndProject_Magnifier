package com.magnifier.enterprise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원가입을 위해서 브라우저에서 받을 데이터를 담을 Dto
 * @author 김경아
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateEnterpriseRequest {
	private int enterpriseId;       // enterprise ID
	private String registerNumber;  // 사업자등록번호
	private String name;             // 기업명
	private String password;    	 // 비밀번호
	private String postNumber;       // 우편번호
	private String address;          // 주소
	private String addressDetail;    // 주소상세
	private String reference;        // 주소-참고사항
	private Boolean enabled;    	 // 로그인 활성화 여부(default: 1)
	private String auth;        	 // 권한(default: ROLE_ENTERPRISE)
}
