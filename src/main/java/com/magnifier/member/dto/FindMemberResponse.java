package com.magnifier.member.dto;

import com.magnifier.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * memberId로 개인정보 조회를 하기 위한 dto
 * @author 김경아
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FindMemberResponse {
	private String loginId;     // 로그인 ID
	private String name;        // 이름
	private Character gender;   // 성별(남: M, 여: F)
	private String phoneNumber; // 휴대폰 번호
	private Integer year;       // 생년월일(연도)
	private Integer month;      // 생년월일(월)
	private Integer day;        // 생년월일(일)
	private String postNumber;  // 우편번호
	private String address;     // 주소
	private String addressDetail; // 주소상세
	private String reference;     // 주소-참고사항
	
	/**
	 * member의 데이터를 넣어서 dto 객체로 생성
	 * @param member
	 * @return findMemberResponse
	 */
	public static FindMemberResponse createFindMemberResponse(Member member) {
		FindMemberResponse findMemberResponse = FindMemberResponse.builder()
				.loginId(member.getLoginId())
				.name(member.getName())
				.gender(member.getGender())
				.phoneNumber(member.getPhoneNumber())
				.year(member.getBirth().getYear())
				.month(member.getBirth().getMonthValue())
				.day(member.getBirth().getDayOfMonth())
				.postNumber(member.getPostNumber())
				.address(member.getAddress())
				.addressDetail(member.getAddressDetail())
				.reference(member.getReference())
				.build();
		return findMemberResponse;
	}
}
