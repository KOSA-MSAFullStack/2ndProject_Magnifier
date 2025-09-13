package com.magnifier.enterprise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원정보 수정을 하기 위해 브라우저에서 받을 데이터를 담을 Dto
 * @author 김경아
 *
 */
@Getter
@Setter
@NoArgsConstructor 
public class UpdateEnterpriseRequest {
	private int enterpriseId;       // memberId
	private String postNumber;  // 우편번호
	private String address;     // 주소
	private String addressDetail; // 주소상세
	private String reference;     // 주소-참고사항
}