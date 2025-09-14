package com.magnifier.applylist.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EnterpriseApplylistDto {
	private String name;				// 회원명
	private String title;				// 공고명
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDate createdAt;		// 지원날짜
	private int memberId;				// 지원한 멤버 ID
}
