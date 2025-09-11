// RecruitController.java
// 역할: 채용 공고 관련 웹 요청 및 응답 처리 (뷰 렌더링)
/*
 * 설명:
 * - 클라이언트의 채용 공고 관련 HTTP 요청 처리
 * - 적절한 뷰(JSP) 반환
 *
 * 주요 기능:
 * - 채용 공고 등록 폼 반환
 * - 채용 공고 목록 페이지 반환
 * - 채용 공고 상세 조회 페이지 반환
 * - 채용 공고 수정 폼 반환
 * - 기업별 등록한 채용 공고 목록 페이지 반환
 * - 채용 공고 삭제 페이지 반환
 */


package com.magnifier.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/recruits")
public class RecruitController {

    // 채용 공고 등록 폼_GET
    @GetMapping("/register")
    public String registerForm() {
        return "recruits/register"; // recruits/register.jsp 뷰 반환
    }

    // 전체 채용 공고 목록 페이지 조회_GET
    @GetMapping(value = "/list", produces = "text/html; charset=UTF-8")
    public String list() {
        // 이 메서드는 뷰만 반환, 데이터는 API 호출을 통해 가져옴
        return "recruits/list";
    }

    // 기업별 등록한 채용 공고 목록 페이지 조회_GET
    @GetMapping("/listbyid")
    public String listById() {
        // 이 메서드는 뷰만 반환, 데이터는 API 호출을 통해 가져옴
        return "recruits/listById";
    }

    // 채용 공고 상세 조회_GET
    @GetMapping("/detail/{recruitId}")
    public String detail(@PathVariable("recruitId") int recruitId) {
        // 이 메서드는 뷰만 반환, 데이터는 API 호출을 통해 가져옴
        return "recruits/detail";
    }

    // 채용 공고 수정 폼_GET
    @GetMapping("/modify/{recruitId}")
    public String modifyForm(@PathVariable("recruitId") int recruitId) {
        // 이 메서드는 뷰만 반환, 데이터는 API 호출을 통해 가져옴
        return "recruits/modify";
    }

    // 채용 공고 삭제 페이지 반환 (실제 삭제는 API에서 처리)
    @GetMapping("/delete/{recruitId}")
    public String deleteForm(@PathVariable("recruitId") int recruitId) {
        // 이 메서드는 뷰만 반환, 데이터는 API 호출을 통해 가져옴
        return "recruits/delete";
    }
}