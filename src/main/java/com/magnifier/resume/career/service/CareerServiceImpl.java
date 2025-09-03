package com.magnifier.resume.career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.career.mapper.CareerMapper;

@Service
public class CareerServiceImpl implements CareerService {
	@Autowired
	private CareerMapper mapper;
	
	@Override
	public void insertCareer(CareerDto dto) {
		mapper.insertCareer(dto);
	}
}
