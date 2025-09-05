package com.magnifier.enterprise.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 김경아
 */
@Getter
@Setter
@NoArgsConstructor
public class EnterpriseDto {
	private int enterpriseId;       // enterprise ID
	private String registerNumber;  // 사업자등록번호
	private String name;             // 기업명
	private String password;    	 // 비밀번호
	private String address;          // 주소
	private Boolean enabled;    	 // 로그인 활성화 여부(default: 1)
	private String auth;        	 // 권한(default: ROLE_ENTERPRISE)
}
