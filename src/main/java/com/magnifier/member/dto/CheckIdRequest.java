package com.magnifier.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
public class CheckIdRequest {
	private String loginId;  // 중복확인할 로그인 ID
}
