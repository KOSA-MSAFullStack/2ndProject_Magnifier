package com.magnifier.enterprise.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.magnifier.enterprise.entity.Enterprise;

@Mapper
public interface EnterpriseMapper {
	public Enterprise read(String registerNumber); // 로그인id로 기업회원 조회
	
	public void save(Enterprise enterprise); // 회원가입
}
