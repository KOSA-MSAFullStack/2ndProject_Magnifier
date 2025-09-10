// RecruitController.java
// 역할: 채용 공고 관련 웹 요청 및 응답 처리
/*
 * 설명:
 * - 클라이언트의 채용 공고 관련 HTTP 요청 처리
 * - 서비스 계층과 연동하여 비즈니스 로직 수행
 * - 적절한 뷰(JSP) 또는 RESTful API 응답(JSON) 반환
 *
 * 주요 기능:
 * - 채용 공고 등록 (폼, 처리)
 * - 채용 공고 목록 조회 (전체, 기업별)
 * - 채용 공고 상세 조회
 * - 채용 공고 수정 (폼, 처리)
 * - 채용 공고 삭제
 */

package com.magnifier.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.service.RecruitService;
import com.magnifier.security.domain.CustomEnterprise;

@Controller
@RequestMapping("/recruits")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    // 채용 공고 등록 폼_GET
    @GetMapping("/register")
    public String registerForm() {
        return "recruits/register"; // recruits/register.jsp 뷰 반환
    }

    // 채용 공고 등록_POST (RESTful API)
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody RecruitDto recruitDto, Authentication auth) {
        try {
            // 현재 로그인한 기업 회원의 ID를 Authentication 객체에서 가져와 설정
            // 보안 강화를 위해 클라이언트에서 보낸 ID가 아닌 서버의 인증 정보 사용
            CustomEnterprise customEnterprise = (CustomEnterprise) auth.getPrincipal();
            recruitDto.setEnterpriseId(customEnterprise.getEnterprise().getEnterpriseId());

            // 채용 공고 등록 서비스 호출
            recruitService.insertRecruit(recruitDto);
            System.out.println("채용 공고 등록: " + recruitDto.getTitle());

            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("채용 공고 등록 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 전체 채용 공고 목록 조회_GET
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            // 서비스에서 페이지네이션 적용된 목록 가져오기
            List<RecruitDto> recruitList = recruitService.getRecruitList(page, size);
            // 전체 아이템 수 가져오기
            int totalCount = recruitService.getCount();
            // 전체 페이지 수 계산
            int totalPages = (int) Math.ceil((double) totalCount / size);

            // 응답 데이터 구성
            Map<String, Object> response = new HashMap<>();
            response.put("recruits", recruitList);
            response.put("currentPage", page);
            response.put("totalPages", totalPages);
            response.put("totalCount", totalCount);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("전체 채용 공고 목록 조회 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        // 기업별 등록한 채용 공고 목록 조회_GET (기존 listById를 manage로 변경하고 뷰도 listById로 변경)
    @GetMapping("/listbyid")
    public String listById(Authentication auth, Model model) {
        try {
            // 현재 로그인한 기업 회원의 ID 가져오기
            CustomEnterprise customEnterprise = (CustomEnterprise) auth.getPrincipal();
            int enterpriseId = customEnterprise.getEnterprise().getEnterpriseId();

            // 해당 기업이 등록한 채용 공고 목록 조회 서비스 호출
            List<RecruitDto> recruitList = recruitService.getRecruitListById(enterpriseId);
            model.addAttribute("recruitList", recruitList);
            return "recruits/listById"; // recruits/listById.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("기업별 등록한 채용 공고 목록 조회 중 오류 발생 (인증된 사용자 ID: "
                    + ((CustomEnterprise) auth.getPrincipal()).getEnterprise().getEnterpriseId() + "): "
                    + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 상세 조회_GET
    @GetMapping("/detail/{recruitId}")
    public String detail(@PathVariable("recruitId") int recruitId, Model model) {
        try {
            // 채용 공고 상세 정보 조회 서비스 호출
            RecruitDto recruitDto = recruitService.detailRecruit(recruitId);
            model.addAttribute("recruitDto", recruitDto);
            return "recruits/detail"; // recruits/detail.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("채용 공고 상세 조회 중 오류 발생 (공고 ID: " + recruitId + "): " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 수정 폼_GET
    @GetMapping("/modify/{recruitId}")
    public String modifyForm(@PathVariable("recruitId") int recruitId, Model model) {
        try {
            // 기존 채용 공고 정보 로드 서비스 호출
            RecruitDto recruitDto = recruitService.detailRecruit(recruitId);
            model.addAttribute("recruitDto", recruitDto);
            return "recruits/modify"; // recruits/modify.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("채용 공고 수정 폼 로드 중 오류 발생 (공고 ID: " + recruitId + "): " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 수정_POST
    @PostMapping("/modify")
    public String modify(RecruitDto recruitDto) {
        try {
            // 채용 공고 수정 서비스 호출
            recruitService.updateRecruit(recruitDto);
            System.out.println("채용 공고 수정: " + recruitDto.getTitle());
            return "redirect:/recruits/detail/" + recruitDto.getRecruitId(); // 수정 성공 시, 상세 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 공고 수정 중 오류 발생: " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 삭제_POST
    @PostMapping("/delete")
    public String delete(RecruitDto recruitDto) {
        try {
            // 채용 공고 삭제 서비스 호출
            recruitService.deleteRecruit(recruitDto);
            System.out.println("채용 공고 삭제 (ID): " + recruitDto.getRecruitId());
            return "redirect:/recruits/list"; // 삭제 성공 시, 목록 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 공고 삭제 중 오류 발생 (ID: " + recruitDto.getRecruitId() + "): " + e.getMessage());
            return "error/errorPage";
        }
    }
}