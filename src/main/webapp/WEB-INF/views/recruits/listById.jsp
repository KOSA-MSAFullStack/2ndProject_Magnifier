<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공고 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/recruit.css">
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="main">
        <div class="recruitList">
            <c:forEach var="recruit" items="${recruitList}">
                <div class="recruit">
                    <div class="recruit-info">
                    <input type="checkbox" class="recruit-checkbox" name="selectedRecruits" value="${recruit.recruitId}">
                    <span class="title">${recruit.title}</span>
                </div>
                    <span class="details">${recruit.workingArea}</span>
                </div>
            </c:forEach>
            <div class="button-container">
                <button class="add-button">공고추가</button>
                <button class="delete-button">공고삭제</button>
            </div>
        </div>
    </div>
</body>
</html>