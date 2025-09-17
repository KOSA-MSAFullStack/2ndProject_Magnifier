package com.magnifier.resume.license.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * @author 이상우
 *
 */
@Data
public class LicenseDto {
	private int licenseId;		// 자격사항_ID
	private int resumeId;		// 이력서_ID
	private String name;		// 자격증명
	private String publisher;	// 발행처/기관
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDate passDate;	// 합격년월
}
