// RecruitControllerTest.java
// 역할: RecruitController 테스트 코드

package com.magnifier.recruit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.service.RecruitService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@WebAppConfiguration
public class RecruitControllerTest {

    @InjectMocks
    private RecruitController recruitController;

    @Mock
    private RecruitService recruitService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recruitController).build();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // 채용 공고 등록 폼 테스트
    @Test
    public void testRegisterForm() throws Exception {
        mockMvc.perform(get("/recruit/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("recruit/register"));
    }

    // 채용 공고 등록 처리 테스트
    @Test
    public void testRegister() throws Exception {
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setTitle("테스트 공고");
        recruitDto.setContent("테스트 내용");
        recruitDto.setCareerCondition("신입");
        recruitDto.setEducation("대졸");
        recruitDto.setEmploymentType("정규직");
        recruitDto.setHeadCount("0명");
        recruitDto.setWorkingArea("서울");
        recruitDto.setSalaryCondition("회사내규");
        recruitDto.setWorkingHours("주 5일");
        recruitDto.setWorkingType("재택");
        recruitDto.setInsurance("4대보험");
        recruitDto.setRetirementSalary("퇴직금");
        recruitDto.setDeadLine(LocalDate.of(2025, 12, 31));
        recruitDto.setStep("서류-면접");
        recruitDto.setContact("010-1234-5678");
        recruitDto.setEnterpriseId(1);

        when(recruitService.insertRecruit(any(RecruitDto.class))).thenReturn(1);

        mockMvc.perform(post("/recruit/register")
                        .param("title", recruitDto.getTitle())
                        .param("content", recruitDto.getContent())
                        .param("careerCondition", recruitDto.getCareerCondition())
                        .param("education", recruitDto.getEducation())
                        .param("employmentType", recruitDto.getEmploymentType())
                        .param("headCount", recruitDto.getHeadCount())
                        .param("workingArea", recruitDto.getWorkingArea())
                        .param("salaryCondition", recruitDto.getSalaryCondition())
                        .param("workingHours", recruitDto.getWorkingHours())
                        .param("workingType", recruitDto.getWorkingType())
                        .param("insurance", recruitDto.getInsurance())
                        .param("retirementSalary", recruitDto.getRetirementSalary())
                        .param("deadLine", recruitDto.getDeadLine().toString())
                        .param("step", recruitDto.getStep())
                        .param("contact", recruitDto.getContact())
                        .param("enterpriseId", recruitDto.getEnterpriseId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/recruit/list"));

        verify(recruitService, times(1)).insertRecruit(any(RecruitDto.class));
    }

    // 채용 공고 목록 조회 테스트
    @Test
    public void testList() throws Exception {
        List<RecruitDto> recruitList = Arrays.asList(new RecruitDto(), new RecruitDto());
        when(recruitService.getRecruitList()).thenReturn(recruitList);

        mockMvc.perform(get("/recruit/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("recruit/list"))
                .andExpect(model().attributeExists("recruitList"));

        verify(recruitService, times(1)).getRecruitList();
    }

    // 기업별 채용 공고 목록 조회 테스트
    @Test
    public void testListById() throws Exception {
        int enterpriseId = 1;
        List<RecruitDto> recruitList = Arrays.asList(new RecruitDto(), new RecruitDto());
        when(recruitService.getRecruitListById(enterpriseId)).thenReturn(recruitList);

        mockMvc.perform(get("/recruit/list/" + enterpriseId))
                .andExpect(status().isOk())
                .andExpect(view().name("recruit/list"))
                .andExpect(model().attributeExists("recruitList"));

        verify(recruitService, times(1)).getRecruitListById(enterpriseId);
    }

    // 채용 공고 상세 조회 테스트
    @Test
    public void testDetail() throws Exception {
        int recruitId = 1;
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(recruitId);
        when(recruitService.detailRecruit(recruitId)).thenReturn(recruitDto);

        mockMvc.perform(get("/recruit/detail/" + recruitId))
                .andExpect(status().isOk())
                .andExpect(view().name("recruit/detail"))
                .andExpect(model().attributeExists("recruitDto"));

        verify(recruitService, times(1)).detailRecruit(recruitId);
    }

    // 채용 공고 수정 폼 테스트
    @Test
    public void testModifyForm() throws Exception {
        int recruitId = 1;
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(recruitId);
        when(recruitService.detailRecruit(recruitId)).thenReturn(recruitDto);

        mockMvc.perform(get("/recruit/modify/" + recruitId))
                .andExpect(status().isOk())
                .andExpect(view().name("recruit/modify"))
                .andExpect(model().attributeExists("recruitDto"));

        verify(recruitService, times(1)).detailRecruit(recruitId);
    }

    // 채용 공고 수정 처리 테스트
    @Test
    public void testModify() throws Exception {
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1);
        recruitDto.setTitle("수정된 공고");
        recruitDto.setContent("수정된 내용");

        doAnswer(invocation -> {
            RecruitDto arg = invocation.getArgument(0);
            arg.setRecruitId(1); // Set the recruitId to the expected value
            return 1; // Return 1 to indicate success
        }).when(recruitService).updateRecruit(any(RecruitDto.class));

        mockMvc.perform(post("/recruit/modify")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recruitDto)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/recruit/detail/" + recruitDto.getRecruitId()));

        verify(recruitService, times(1)).updateRecruit(any(RecruitDto.class));
    }

    // 채용 공고 삭제 처리 테스트
    @Test
    public void testDelete() throws Exception {
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1);

        when(recruitService.deleteRecruit(any(RecruitDto.class))).thenReturn(1);

        mockMvc.perform(post("/recruit/delete")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recruitDto)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/recruit/list"));

        verify(recruitService, times(1)).deleteRecruit(any(RecruitDto.class));
    }
}
