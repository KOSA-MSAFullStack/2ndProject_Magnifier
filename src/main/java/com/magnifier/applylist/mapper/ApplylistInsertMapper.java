// ApplylistInsertMapper.java
// 지원 목록 삽입 매퍼 인터페이스

package com.magnifier.applylist.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.magnifier.applylist.entity.Applylist;

// * author: 김기성 
@Mapper
public interface ApplylistInsertMapper {

	// 지원 정보 추가
	void insertApplylist(Applylist applylist);

	// 회원 ID와 채용 공고 ID로 지원 내역 조회
	Applylist selectApplylistByMemberAndRecruit(@Param("memberId") Integer memberId, @Param("recruitId") Integer recruitId);
}