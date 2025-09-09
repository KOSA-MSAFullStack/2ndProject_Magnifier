<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그아웃</title>
<link rel="stylesheet" href="/resources/css/logout.css">
</head>
<body>
	<div class="logout-container">
		<div class="logout-title">로그아웃 하시겠습니까?</div>
				<%
		  org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
		  if (auth != null) {
		    out.println("로그인 사용자: " + auth.getName());
		    out.println("권한: " + auth.getAuthorities());
		  } else {
		    out.println("비로그인 상태");
		  }
		%>
<p>현재 로그인한 사용자: <security:authentication property="name" /></p>
		<!-- 개인 회원일 때 로그아웃 요청 -->
		<security:authorize access="hasRole('ROLE_MEMBER')">
			<!-- 스프링 시큐리티가 자동으로 세션 삭제 -->
			<form action="/members/logout" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button type="submit" class="logout-btn">로그아웃</button>
			</form>
		</security:authorize>

		<!-- 기업 회원일 때 로그아웃 요청 -->
		<security:authorize access="hasRole('ROLE_ENTERPRISE')">
			<!-- 스프링 시큐리티가 자동으로 세션 삭제 -->
			<form action="/enterprises/logout" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button type="submit" class="logout-btn">로그아웃</button>
			</form>
		</security:authorize>
	</div>
</body>
</html>