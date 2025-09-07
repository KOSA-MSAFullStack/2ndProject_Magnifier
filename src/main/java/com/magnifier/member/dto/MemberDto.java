package com.magnifier.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
public class MemberDto {
	private String loginId;     // 로그인 ID
	private String name;        // 이름
	private Character gender;   // 성별(남: M, 여: F)
	private String password;    // 비밀번호
	private String passwordConfirm;    // 비밀번호 확인
	private String phoneNumber; // 휴대폰 번호
	private Integer year;    // 생년월일(연도)
	private Integer month;    // 생년월일(월)
	private Integer day;    // 생년월일(일)
	private String postNumber;    // 우편번호
	private String address;     // 주소
	private String addressDetail;     // 주소상세
	private String reference;     // 주소-참고사항
}
