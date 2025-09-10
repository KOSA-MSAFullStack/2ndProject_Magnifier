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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
            <!-- 공고 목록이 동적으로 여기에 추가됩니다. -->
        </div>
        <div class="pagination">
            <!-- 페이지네이션이 동적으로 여기에 추가됩니다. -->
        </div>
    </div>

<script>
$(document).ready(function() {
    // 페이지 로드 시 Ajax 통해 공고 목록 로드
    $.ajax({
        url: "${pageContext.request.contextPath}/recruits/list",
        type: "GET",
        dataType: "json",
        success: function(data) {
            const recruitListContainer = $('.recruitList');
            // 데이터가 없거나 비어있는 경우 메시지 표시
            if (!data || data.length === 0) {
                recruitListContainer.html("<p>등록된 채용 공고가 없습니다.</p>");
                return;
            }

            // 각 공고에 대한 HTML 생성, 컨테이너에 추가
            data.forEach(function(recruit) {
                const recruitHtml = `
                    <div class="recruit">
                        <span class="title">${recruit.title}</span>
                        <span class="details">${recruit.workingArea}</span>
                        <div class="apply">
                            <a href="${pageContext.request.contextPath}/recruits/detail/${recruit.recruitId}" class="applyButton">지원하기</a>
                            <span class="deadline">마감일 : ${recruit.deadLine}</span>
                        </div>
                    </div>
                `;
                recruitListContainer.append(recruitHtml);
            });

            // TODO: 페이지네이션 로직 구현 (현재는 정적)
            const paginationHtml = `
                <a>이전</a>
                <a>1</a>
                <a>2</a>
                <a>3</a>
                <a>4</a>
                <a>5</a>
                <a>다음</a>
            `;
            $(".pagination").html(paginationHtml);
        },
        error: function(xhr, status, error) {
            console.error("공고 목록을 불러오는 중 오류 발생: ", error);
            $(".recruitList").html("<p>공고를 불러오는 중 오류가 발생했습니다.</p>");
        }
    });
});
</script>

</body>
</html>