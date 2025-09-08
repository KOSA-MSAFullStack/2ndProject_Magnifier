package com.magnifier.member.entity;

import java.time.LocalDate;

import com.magnifier.member.dto.CreateMemberRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private String address;     // 주소
	private Boolean enabled;    // 로그인 활성화 여부(default: 1)
	private String auth;        // 권한(default: ROLE_MEMBER)
	
	/**
	 *  memberDto -> member 빌더로 생성
	 * @param memberDto
	 * @return
	 */
	public static Member createMember(CreateMemberRequest dto, String encodePW, LocalDate birth) {
		Member member = Member.builder()
				.loginId(dto.getLoginId())
				.name(dto.getName())
				.gender(dto.getGender())
				.password(encodePW)
				.phoneNumber(dto.getPhoneNumber())
				.birth(birth)
				.address(dto.getAddress())
				.build();
		return member;
	}
}
