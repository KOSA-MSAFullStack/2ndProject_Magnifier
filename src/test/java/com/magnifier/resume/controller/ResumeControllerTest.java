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
import com.magnifier.resume.dto.ResumeDto;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @author 이상우
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ResumeControllerTest {

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
        ResumeDto dto = new ResumeDto();
        dto.setMemberId(1);
        dto.setTitle("test title");
        dto.setSchoolType("고등학교");
        dto.setSchoolName("대전고등학교");
        dto.setGraduateStatus("졸업");
        dto.setEnterDate(LocalDate.of(2010, 3, 1));
        dto.setGraduateDate(LocalDate.of(2013, 2, 1));

        // 2. DTO 객체를 JSON 문자열로 변환
        String jsonContent = objectMapper.writeValueAsString(dto);
        log.info("JSON Content: " + jsonContent);

        // 3. POST 요청 시뮬레이션
        mockMvc.perform(post("/resumes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk()); // HTTP 상태 코드가 200 OK인지 확인
    }
}
