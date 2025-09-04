package com.magnifier.resume.controller;

import com.magnifier.resume.dto.ResumeDto;
import com.magnifier.resume.service.ResumeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class ResumeDetailControllerTest {

    @Mock
    private ResumeService service;

    @InjectMocks
    private ResumeController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testFindResumesByMemberId_Success() throws Exception {
        // 서비스가 이력서 목록을 성공적으로 반환하도록 Mocking 설정
        int memberId = 1;
        List<ResumeDto> dummyResumes = Collections.singletonList(new ResumeDto());
        when(service.findResumesByMemberId(memberId)).thenReturn(dummyResumes);

        // GET 요청을 수행하고 응답 상태를 검증 (200 OK)
        mockMvc.perform(get("/resumes/{memberId}", memberId))
                .andExpect(status().isOk()); // Expecting a 200 OK status
    }

    @Test
    public void testFindResumesByMemberId_NoContent() throws Exception {
        // 서비스가 빈 목록을 반환하도록 Mocking 설정
        int memberId = 2;
        when(service.findResumesByMemberId(memberId)).thenReturn(Collections.emptyList());

        // GET 요청을 수행하고 응답 상태를 검증 (204 No Content)
        mockMvc.perform(get("/resumes/{memberId}", memberId))
                .andExpect(status().isNoContent()); // Expecting a 204 No Content status
    }

    @Test
    public void testFindResumesByMemberId_InternalServerError() throws Exception {
        // 서비스에서 예외가 발생하도록 Mocking 설정
        int memberId = 3;
        when(service.findResumesByMemberId(memberId)).thenThrow(new RuntimeException("서비스 실패"));

        // GET 요청을 수행하고 응답 상태를 검증 (500 Internal Server Error)
        mockMvc.perform(get("/resumes/{memberId}", memberId))
                .andExpect(status().isInternalServerError()); // Expecting a 500 Internal Server Error
    }
}