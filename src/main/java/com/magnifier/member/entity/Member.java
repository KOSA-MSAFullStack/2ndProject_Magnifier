package com.magnifier.member.entity;

import java.time.LocalDate;

import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.dto.UpdateMemberRequest;

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
public class Member {
	private int memberId;       // member ID
	private String loginId;     // 로그인 ID
	private String name;        // 이름
	private Character gender;   // 성별(남: M, 여: F)
	private String password;    // 비밀번호
	private String phoneNumber; // 휴대폰 번호
	private LocalDate birth;    // 생년월일
	private String postNumber;  // 우편번호
	private String address;     // 주소
	private String addressDetail; // 상세주소
	private String reference;     // 참고사항
	private Boolean enabled;    // 로그인 활성화 여부(default: 1)
	private String auth;        // 권한(default: ROLE_MEMBER)
	
	/**
	 * 회원 가입 요청 dto -> Member 객체로 생성
	 * @param dto(CreateMemberRequest)
	 * @param encodePW
	 * @param birth
	 * @return Member
	 */
	public static Member createMember(CreateMemberRequest dto, String encodePW, LocalDate birth) {
		Member member = Member.builder()
				.loginId(dto.getLoginId())
				.name(dto.getName())
				.gender(dto.getGender())
				.password(encodePW)
				.phoneNumber(dto.getPhoneNumber())
				.birth(birth)
				.postNumber(dto.getPostNumber())
				.address(dto.getAddress())
				.addressDetail(dto.getAddressDetail())
				.reference(dto.getReference())
				.build();
		return member;
	}
	
	/**
	 * 회원 수정 요청 dto -> Member 객체로 생성
	 * @param dto(UpdateMemberRequest)
	 * @param birth
	 * @return Member
	 */
	public static Member createMember(UpdateMemberRequest dto, LocalDate birth) {
		Member member = Member.builder()
				.memberId(dto.getMemberId())
				.gender(dto.getGender())
				.phoneNumber(dto.getPhoneNumber())
				.birth(birth)
				.postNumber(dto.getPostNumber())
				.address(dto.getAddress())
				.addressDetail(dto.getAddressDetail())
				.reference(dto.getReference())
				.build();
		return member;
	}
}
