package com.magnifier.applylist.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MemberApplylistDto {
	private String name;             	// 기업명
	private String title;				// 공고 제목
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDate createdAt;		// 지원날짜
	private int recruitId;				// 공고 ID
}
