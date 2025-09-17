package com.magnifier.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

/**
 * 로그인 성공 후 실행되는 처리 로직을 담당하는 핸들러
 * @author 김경아
 *
 */
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	/**
     * 인증(로그인) 성공 시 호출되는 메서드
     * @param request    현재 HTTP 요청 객체
     * @param response   HTTP 응답 객체
     * @param auth       인증된 사용자 정보(Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        // 로그인 성공 로그 출력
        log.warn("Login Success");
        log.warn("auth: " + auth);
        
        // 사용자 정보를 세션에 저장
        request.getSession().setAttribute("user", auth.getPrincipal());

        // 사용자의 권한(ROLE)을 수집하는 리스트
        List<String> roleNames = new ArrayList<>();
        auth.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        }); // end auth.getAuthorities

        // 권한 목록을 로그에 출력
        log.warn("ROLE NAMES: " + roleNames);

        // 권한에 따라 다른 URL로 리다이렉트
        if (roleNames.contains("ROLE_ENTERPRISE")) {
            // ROLE_ENTERPRISE이 있으면 '채용공고' 페이지로 이동
            response.sendRedirect("/recruits/list");
            return;
        } // end if

        if (roleNames.contains("ROLE_MEMBER")) {
            // ROLE_MEMBER가 있으면 '채용공고' 페이지로 이동
            response.sendRedirect("/recruits/list");
            return;
        } // end if

        // 기본적으로 '/login'(홈)로 이동
        response.sendRedirect("/login");
        
    } // end onAuthenticationSuccess
} // end class