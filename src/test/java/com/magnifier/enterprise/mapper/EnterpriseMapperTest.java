package com.magnifier.enterprise.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.enterprise.entity.Enterprise;

import lombok.extern.log4j.Log4j;

/**
 * ID로 enterprise 테이블에서 회원 조회되는지 test
 * @author 김경아
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  // SpringJUnit4ClassRunner를 사용해 Spring 환경에서 테스트 실행
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" }) // 테스트에 필요한 컨텍스트 설정 파일 경로 지정
@Log4j
public class EnterpriseMapperTest {
	
	@Autowired
	private EnterpriseMapper mapper; // EnterpriseMapper 자동 주입
	
	@Test
	public void testRead() {
		String registerNumber = "1248100998"; // 조회할 ID
		Enterprise enterprise = mapper.read(registerNumber); // mapper로 회원 조회
		log.info("dto : " + enterprise);
	}// end testRead()
}// end class
