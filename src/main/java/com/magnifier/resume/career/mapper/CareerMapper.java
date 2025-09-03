package com.magnifier.resume.career.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.resume.career.dto.CareerDto;

@Mapper
public interface CareerMapper {
	public void insertCareer(CareerDto careerDto);
}
