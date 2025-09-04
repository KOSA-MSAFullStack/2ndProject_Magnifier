<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인 | 톱보기</title>
    <link rel="stylesheet" href="/resources/css/memberLogin.css">
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="background">
        <header class="navbar">
            <img class="logo" src="https://placehold.co/408x408" alt="로고">
            <nav class="nav-menu">
                <a href="#">채용공고</a>
                <a href="#">이력서관리</a>
                <a href="#">지원내역</a>
                <a href="#">내 정보</a>
                <a href="#" class="active">로그인</a>
            </nav>
        </header>
        <main class="main-center">
            <div class="login-card">
                <div class="login-tabs">
                    <div class="login-tab active">개인 회원 로그인</div>
                    <div class="login-tab">기업 회원 로그인</div>
                </div>
                <form class="login-form">
                    <div class="form-fields">
                        <input type="text" placeholder="아이디" name="username" />
                        <input type="password" placeholder="비밀번호" name="password" />
                    </div>
                    <button type="submit" class="login-button">로그인</button>
                </form>
                <div class="login-links">
                    <a href="#">아이디 찾기</a> | 
                    <a href="#">비밀번호 찾기</a> | 
                    <a href="#">회원가입</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
