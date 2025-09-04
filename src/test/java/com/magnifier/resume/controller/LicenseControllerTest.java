package com.magnifier.resume.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.magnifier.resume.career.dto.CareerDto;
import com.magnifier.resume.license.dto.LicenseDto;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class LicenseControllerTest {
	
	@Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void testRegister() throws Exception {
        // 1. 테스트용 DTO 객체 생성
    	LicenseDto dto = new LicenseDto();
    	dto.setResumeId(1);
    	dto.setName("정보처리기사");
    	dto.setPublisher("큐넷");
    	dto.setPassDate(LocalDate.of(1996,05,01));

        // 2. DTO 객체를 JSON 문자열로 변환
        String jsonContent = objectMapper.writeValueAsString(dto);
        log.info("JSON Content: " + jsonContent);

        // 3. POST 요청 시뮬레이션
        mockMvc.perform(post("/resumes/licenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk()); // HTTP 상태 코드가 200 OK인지 확인
    }
}
