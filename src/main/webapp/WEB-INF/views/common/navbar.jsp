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
    <a href="/recruits/list">채용공고</a>

	<!-- 기업회원 : 공고관리, 개인회원 및 일반인 : 이력서관리 -->
    <security:authorize access="hasRole('ROLE_ENTERPRISE')">
      <a href="/recruits/listbyid">공고관리</a>
    </security:authorize>

	<security:authorize access="!hasRole('ROLE_ENTERPRISE')">
      <a href="/resumes/">이력서관리</a>
	</security:authorize>
	
    <!-- 개인회원 : 개인이 지원한 내역, 기업회원: 기업 공고에 지원한 지원자 내역 -->
    <security:authorize access="hasRole('ROLE_ENTERPRISE')">
        <a href=/enterprises/applylist>지원내역</a>
    </security:authorize>
    <security:authorize access="!hasRole('ROLE_ENTERPRISE')">
        <a href="/members/applylist">지원내역</a>
    </security:authorize>
    
    <!-- 개인회원 : 개인회원정보, 기업회원: 기업회원정보 -->
    <security:authorize access="hasRole('ROLE_ENTERPRISE')">
        <a href=/enterprises/mypage>내 정보</a>
    </security:authorize>
    <security:authorize access="!hasRole('ROLE_ENTERPRISE')">
        <a href="/members/mypage">내 정보</a>
    </security:authorize>

	<!-- 로그인 되어있을 때는 로그아웃이 보이도록, 로그아웃 상태면 로그인이 보이도록 -->
    <security:authorize access="isAuthenticated()">
	    <security:authorize access="hasRole('ROLE_ENTERPRISE')">
	        <form id="logoutForm" action="/enterprises/logout" method="post" style="display:none;">
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        </form>
	        <a href="#" onclick="document.getElementById('logoutForm').submit(); return false;">로그아웃</a>
	    </security:authorize>
	    <security:authorize access="!hasRole('ROLE_ENTERPRISE')">
	        <form id="logoutForm" action="/members/logout" method="post" style="display:none;">
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        </form>
	        <a href="#" onclick="document.getElementById('logoutForm').submit(); return false;">로그아웃</a>
	    </security:authorize>
	</security:authorize>
    
    <security:authorize access="!isAuthenticated()">
      <a href="members/login">로그인</a>
    </security:authorize>
  </nav>
</header>


