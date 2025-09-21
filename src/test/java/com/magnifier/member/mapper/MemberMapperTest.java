package com.magnifier.member.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.magnifier.member.dto.CreateMemberRequest;
import com.magnifier.member.entity.Member;

import lombok.extern.log4j.Log4j;

/**
 * ID로 memebr 테이블에서 회원 조회되는지 test
 * @author 김경아
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit4ClassRunner를 사용해 Spring 환경에서 테스트 실행
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" }) // 테스트에 필요한 컨텍스트 설정 파일 경로 지정
@Log4j
public class MemberMapperTest {

	@Autowired
	private MemberMapper mapper; // MemberMapper 자동 주입

	@Test
	public void testRead() {
		String username = "ruddk1221"; // 조회할 ID
		Member member = mapper.read(username); // mapper로 회원 조회
		log.info("member : " + member);

	}// end testRead()
}// end class
