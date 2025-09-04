package com.magnifier.resume.career.service;

import com.magnifier.resume.career.dto.CareerDto;

public interface CareerService {
	// 경력사항 추가
	public void insertCareer(CareerDto dto);
	
	// 경력사항 수정
	public int modifyCareer(CareerDto dto);
	
	// 경력사항 삭제
	public int deleteCareer(int id);
}
