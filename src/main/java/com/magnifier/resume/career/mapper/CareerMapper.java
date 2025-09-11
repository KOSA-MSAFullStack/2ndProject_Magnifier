package com.magnifier.resume.career.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.resume.career.dto.CareerDto;

@Mapper
public interface CareerMapper {
	// 경력사항 추가
	public void insertCareer(CareerDto dto);
	
	// 경력사항 수정
	public int modifyCareer(CareerDto dto);
	
	// 경력사항 삭제
	public int deleteCareer(int id);
	
	// 이력서에 있는 경력사항 리스트 조회
	public List<Integer> findCareerIdsByResumeId(int resumeId);
}
