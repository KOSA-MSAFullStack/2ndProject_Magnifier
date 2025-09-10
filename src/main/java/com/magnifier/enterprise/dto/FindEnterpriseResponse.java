package com.magnifier.enterprise.dto;

import com.magnifier.enterprise.entity.Enterprise;
import com.magnifier.member.dto.FindMemberResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
public class FindEnterpriseResponse {
	private String registerNumber;   // 사업자등록번호
	private String name;             // 기업명
	private String postNumber;       // 우편번호
	private String address;          // 주소
	private String addressDetail;    // 주소상세
	private String reference;        // 주소-참고사항
	
	/**
	 * enterprise의 데이터를 넣어서 dto 객체로 생성
	 * @param enterprise
	 * @return findEnterpriseResponse
	 */
	public static FindEnterpriseResponse createFindEnterpriseResponse(Enterprise enterprise) {
		FindEnterpriseResponse findEnterpriseResponse = FindEnterpriseResponse.builder()
				.name(enterprise.getName())
				.registerNumber(enterprise.getRegisterNumber())
				.postNumber(enterprise.getPostNumber())
				.address(enterprise.getAddress())
				.addressDetail(enterprise.getAddressDetail())
				.reference(enterprise.getReference())
				.build();
		return findEnterpriseResponse;
	}
}
