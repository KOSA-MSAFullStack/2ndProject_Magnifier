package com.magnifier.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 아이디 중복확인을 위해 받을 로그인 ID를 담아둔 Dto
 * @author 김경아
 *
 */
@Getter
@Setter
@NoArgsConstructor 
public class CheckIdRequest {
	private String loginId;  // 중복확인할 로그인 ID
}
