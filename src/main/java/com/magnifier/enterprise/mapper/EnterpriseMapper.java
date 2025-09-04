package com.magnifier.enterprise.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.enterprise.dto.EnterpriseDto;

@Mapper
public interface EnterpriseMapper {
	public EnterpriseDto read(String registerNumber);
}
