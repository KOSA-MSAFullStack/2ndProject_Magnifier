<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <link rel="stylesheet" href="/resources/css/signup.css" />
    <link rel="stylesheet" href="/resources/css/common.css" />
</head>
<body>
    <div class="container">
        <div class="background"></div>
       	<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
        <div class="page-title">회원가입</div>
        
        <form class="signup-form" method="post" action="/member/signup">
            <div class="form-group name-group">
                <input type="text" name="name" placeholder="이름" class="input-box medium" />
                <div class="gender-options">
                    <div class="radio-box">
                        <input type="radio" id="male" name="gender" />
                        <label for="male">남</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="female" name="gender" />
                        <label for="female">여</label>
                    </div>
                </div>
            </div>
            <div class="form-group id-group">
                <input type="text" name="loginId" placeholder="아이디" class="input-box medium" />
                <button type="button" class="btn-duplicate-check">중복 확인</button>
            </div>
            <div class="form-group password-group">
                <input type="password" name="password" placeholder="비밀번호" class="input-box large" />
            </div>
            <div class="form-group password-confirm-group">
                <input type="password" placeholder="비밀번호 확인" class="input-box large" />
            </div>
            <div class="form-group phone-group">
                <input type="text" name="phoneNumber" placeholder="휴대폰 번호 (번호만 작성해주세요. 예시:010XXXXXXXX )" class="input-box large" />
            </div>
            
            <div class="form-group birth-group">
                <!-- <div class="label">생년월일을 입력해주세요 :</div> -->
                <select class="select-box year" name="year"><option selected disabled>생년월일(년도)</option></select>
                <select class="select-box month" name="month"><option selected disabled>생년월일(월)</option></select>
                <select class="select-box day" name="day"><option selected disabled>생년월일(일)</option></select>
            </div>
            
            <div class="form-group postal-group">
                <input type="text" name="postNumber" placeholder="우편번호" class="input-box medium" />
                <button type="button" class="btn-postal-search">우편번호 찾기</button>
            </div>
            
            <div class="form-group">
                <input type="text" name="address" placeholder="주소" class="input-box" />
            </div>

            <div class="form-group address-group">
                <input type="text" name="addressDetail" placeholder="상세 주소" class="input-box" />
                <input type="text" name="reference" placeholder="참고 항목" class="input-box" />
            </div>
            
            <button type="submit" class="btn-submit">가입하기</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>
</html>