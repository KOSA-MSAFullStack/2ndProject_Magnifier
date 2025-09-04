package com.magnifier.member.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.member.dto.MemberDto;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class MemberMapperTest {

	@Autowired
	private MemberMapper mapper;

	@Test
	public void testRead() {
		String username = "ruddk1221";
		MemberDto dto = mapper.read(username);
		log.info("dto : " + dto);

	}// end testRead()
}// end class
