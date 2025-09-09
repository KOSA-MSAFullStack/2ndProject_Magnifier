<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용공고</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/recruit.css">
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="main">
        <div class="search">
            <select class="searchSelect">
                <option value="1">지역</option>
                <option value="2">서울</option>
                <option value="3">대전</option>
                <option value="4">부산</option>
            </select>
            <input type="text" placeholder="찾고있는 공고를 검색해주세요.">
            <button class="searchButton">검색</button>
        </div>
        <div class="recruitList">
            <c:forEach var="recruit" items="${recruitList}">
                <div class="recruit">
                    <span class="title">${recruit.title}</span>
                    <%-- As per the DTO, company name is not available. Displaying working area instead. --%>
                    <span class="details">${recruit.workingArea}</span>
                    <div class="apply">
                        <a href="${pageContext.request.contextPath}/recruits/detail/${recruit.recruitId}" class="applyButton">지원하기</a>
                        <span class="deadline">마감일 : ${recruit.deadLine}</span>
                    </div>
                </div>
            </c:forEach>
            
            <%-- Static Pagination --%>
            <div class="pagination">
                <a>이전</a>
                <a>1</a>
                <a>2</a>
                <a>3</a>
                <a>4</a>
                <a>5</a>
                <a>다음</a>
            </div>
        </div>
    </div>
</body>
</html>
