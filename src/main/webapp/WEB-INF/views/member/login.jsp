<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인 | 개인회원</title>
    <link rel="stylesheet" href="/resources/css/login.css">
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="background">
    <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
        <main class="main-center">
            <div class="login-card">
                <div class="login-tabs">
                    <div class="login-tab active">개인 회원 로그인</div>
                    <div class="login-tab"><a href="/enterprise/login">기업 회원 로그인</a></div>
                </div>
                <form class="login-form" method="post" action="/member/loginProcess">
                    <div class="form-fields">
                        <input type="text" placeholder="아이디" name="username" />
                        <input type="password" placeholder="비밀번호" name="password" />
                    </div>
                    <button type="submit" class="login-button">로그인</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <span class="error-msg"><c:out value="${error}" /></span>
                <div class="login-links">
                    <a href="#">아이디 찾기</a> | 
                    <!-- <a href="#">비밀번호 찾기</a> |  -->
                    <a href="#">회원가입</a>
                </div>
            </div>
        </main>
