// RecruitControllerTest.java
// 역할: RecruitController 웹 계층 테스트
/*
 * 설명:
 * - RecruitController의 HTTP 요청 처리 로직 검증
 *
 * 주요 기능:
 * - 채용 공고 등록 (폼, 처리) 테스트
 * - 채용 공고 목록 조회 (전체, 기업별) 테스트
 * - 채용 공고 상세 조회 테스트
 * - 채용 공고 수정 (폼, 처리) 테스트
 * - 채용 공고 삭제 테스트
 */

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
         // GET /recruits/register 요청 수행
         mockMvc.perform(get("/recruits/register"))
                 .andExpect(status().isOk())
                 .andExpect(view().name("recruits/register"));
     }
 
     // 채용 공고 등록 처리 테스트
     @Test
     public void testRegister() throws Exception {
         // 테스트용 RecruitDto 객체 생성 및 데이터 설정
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
 
         // POST /recruits/register 요청 수행 및 파라미터 설정
         mockMvc.perform(post("/recruits/register")
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
                 .andExpect(redirectedUrl("/recruits/list")); // "/recruits/list"로 리다이렉트 검증
 
         // recruitService.insertRecruit 메서드가 1번 호출되었는지 검증
         verify(recruitService, times(1)).insertRecruit(any(RecruitDto.class));
     }
 
     // 채용 공고 목록 조회 테스트
     @Test 
     public void testList() throws Exception {
         // 테스트용 채용 공고 목록 생성
         List<RecruitDto> recruitList = Arrays.asList(new RecruitDto(), new RecruitDto());
         when(recruitService.getRecruitList()).thenReturn(recruitList);
 
         // GET /recruits/list 요청 수행
         mockMvc.perform(get("/recruits/list"))
                 .andExpect(status().isOk())
                 .andExpect(view().name("recruits/list")) // 뷰 이름 "recruit/list" 검증
                 .andExpect(model().attributeExists("recruitList")); // 모델에 "recruitList" 속성 존재 검증
 
         verify(recruitService, times(1)).getRecruitList();
     }
 
     // 기업별 채용 공고 목록 조회 테스트
     @Test
     public void testListById() throws Exception {
         int enterpriseId = 1;
         // 테스트용 채용 공고 목록 생성
         List<RecruitDto> recruitList = Arrays.asList(new RecruitDto(), new RecruitDto());
         when(recruitService.getRecruitListById(enterpriseId)).thenReturn(recruitList);
 
         // GET /recruits/list/{enterpriseId} 요청 수행
         mockMvc.perform(get("/recruits/list/" + enterpriseId))
                 .andExpect(status().isOk())
                 .andExpect(view().name("recruits/listById")) // 뷰 이름 "recruit/list" 검증
                 .andExpect(model().attributeExists("recruitList")); // 모델에 "recruitList" 속성 존재 검증
 
         verify(recruitService, times(1)).getRecruitListById(enterpriseId);
     }
 
     // 채용 공고 상세 조회 테스트
     @Test
     public void testDetail() throws Exception {
         int recruitId = 1;
         RecruitDto recruitDto = new RecruitDto();
         recruitDto.setRecruitId(recruitId);
         when(recruitService.detailRecruit(recruitId)).thenReturn(recruitDto);
 
         // GET /recruits/detail/{recruitId} 요청 수행
         mockMvc.perform(get("/recruits/detail/" + recruitId))
                 .andExpect(status().isOk())
                 .andExpect(view().name("recruits/detail"))
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
 
         // GET /recruits/modify/{recruitId} 요청 수행
         mockMvc.perform(get("/recruits/modify/" + recruitId))
                 .andExpect(status().isOk())
                 .andExpect(view().name("recruits/modify"))
                 .andExpect(model().attributeExists("recruitDto"));
 
         verify(recruitService, times(1)).detailRecruit(recruitId);
     }
 
     // 채용 공고 수정 처리 테스트
     @Test
     public void testModify() throws Exception {
         // 테스트용 RecruitDto 객체 생성 및 데이터 설정
         RecruitDto recruitDto = new RecruitDto();
         recruitDto.setRecruitId(1);
         recruitDto.setTitle("수정된 공고");
         recruitDto.setContent("수정된 내용");
 
         doAnswer(invocation -> {
             RecruitDto arg = invocation.getArgument(0);
             arg.setRecruitId(1);
             return 1;
         }).when(recruitService).updateRecruit(any(RecruitDto.class));
 
         // POST /recruits/modify 요청 수행 및 JSON 데이터 전송
         mockMvc.perform(post("/recruits/modify")
                         .contentType("application/json")
                         .content(objectMapper.writeValueAsString(recruitDto)))
                 .andExpect(status().is3xxRedirection())
                 .andExpect(redirectedUrl("/recruits/detail/" + recruitDto.getRecruitId()));
 
         verify(recruitService, times(1)).updateRecruit(any(RecruitDto.class));
     }
 
     // 채용 공고 삭제 처리 테스트
     @Test
     public void testDelete() throws Exception {
         // 테스트용 RecruitDto 객체 생성 및 데이터 설정
         RecruitDto recruitDto = new RecruitDto();
         recruitDto.setRecruitId(1);
 
         when(recruitService.deleteRecruit(any(RecruitDto.class))).thenReturn(1);
 
         // POST /recruits/delete 요청 수행 및 JSON 데이터 전송
         mockMvc.perform(post("/recruits/delete")
                         .contentType("application/json")
                         .content(objectMapper.writeValueAsString(recruitDto)))
                 .andExpect(status().is3xxRedirection())
                 .andExpect(redirectedUrl("/recruits/list"));
 
         verify(recruitService, times(1)).deleteRecruit(any(RecruitDto.class));
     }
 }
 