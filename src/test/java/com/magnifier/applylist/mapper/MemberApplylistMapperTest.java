package com.magnifier.applylist.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.magnifier.applylist.dto.MemberApplylistDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberApplylistMapperTest {

	@Autowired
	private ApplylistMapper mapper;
	
	@Test
    public void testResumeRegister() {
    	int memberId = 5;
    	List<MemberApplylistDto> dto = mapper.selectApplylistsByMemberId(memberId);
    	System.out.println(dto);
    }
}
