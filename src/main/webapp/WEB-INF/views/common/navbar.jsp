<!-- commonNavbar.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<header class="navbar">
  <div class="logo">
    <a href="#"><img src="/resources/images/logo.png" alt="로고"></a>
  </div>
  <nav class="nav-menu">
    <a href="#">채용공고</a>

	<!-- 기업회원 : 공고관리, 개인회원 : 이력서관리 -->
    <security:authorize access="hasRole('ROLE_ENTERPRISE')">
      <a href="#">공고관리</a>
    </security:authorize>

    <a href="#">이력서관리</a>

    <a href="#">지원내역</a>
    
    <a href="#">내 정보</a>

	<!-- 로그인 되어있을 때는 로그아웃이 보이도록, 로그아웃 상태면 로그인이 보이도록 -->
    <security:authorize access="isAuthenticated()">
      <form id="logoutForm" action="/members/logout" method="post" style="display:none;">
  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	  </form>
	  <a href="#" onclick="document.getElementById('logoutForm').submit(); return false;">로그아웃</a>
    </security:authorize>
    
    <security:authorize access="!isAuthenticated()">
      <a href="members/login">로그인</a>
    </security:authorize>
  </nav>
</header>


