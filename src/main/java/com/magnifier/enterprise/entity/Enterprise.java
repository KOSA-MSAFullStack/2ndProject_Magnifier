package com.magnifier.enterprise.entity;

import com.magnifier.enterprise.dto.CreateEnterpriseRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DB에 저장할 필드
 * @author 김경아
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Enterprise {
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
	
	/**
	 * dto의 값이나 서비스계층에서 재조합한 데이터를 빌더로 값을 채워
	 * @param dto
	 * @param encodePW
	 * @return
	 */
	public static Enterprise createEnterprise(CreateEnterpriseRequest dto, String encodePW) {
		Enterprise enterprise = Enterprise.builder()
				.name(dto.getName())
				.registerNumber(dto.getRegisterNumber())
				.password(encodePW)
				.postNumber(dto.getPostNumber())
				.address(dto.getAddress())
				.addressDetail(dto.getAddressDetail())
				.reference(dto.getReference())
				.build();
		return enterprise;
	}
}
