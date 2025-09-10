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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="main">
        <div class="recruitList">
            <!-- 기업별 공고 목록이 동적으로 여기에 추가됩니다. -->
        </div>
        <div class="button-container">
            <button class="add-button" onclick="location.href='${pageContext.request.contextPath}/recruits/register'">공고 추가</button>
            <button class="delete-button onclick="location.href='${pageContext.request.contextPath}/recruits/delete'">공고 삭제</button>
        </div>
    </div>

<script>
$(document).ready(function() {
    $.ajax({
        url: "${pageContext.request.contextPath}/recruits/listbyid",
        type: "GET",
        dataType: "json",
        headers: {
            'X-CSRF-TOKEN': '${_csrf.token}' // GET 요청이지만 보안 컨텍스트 유지를 위해 포함
        },
        success: function(data) {
            const recruitListContainer = $('.recruitList');
            if (!data || data.length === 0) {
                recruitListContainer.html("<p>등록된 채용 공고가 없습니다.</p>");
                return;
            }

            data.forEach(function(recruit) {
                const recruitHtml = `
                    <div class="recruit">
                        <div class="recruit-info">
                            <input type="checkbox" class="recruit-checkbox" name="selectedRecruits" value="${recruit.recruitId}">
                            <span class="title">${recruit.title}</span>
                        </div>
                        <span class="details">${recruit.workingArea}</span>
                    </div>
                `;
                recruitListContainer.append(recruitHtml);
            });
        },
        error: function(xhr, status, error) {
            console.error("공고 목록을 불러오는 중 오류 발생: ", error);
            if (xhr.status === 403) {
                 $(".recruitList").html("<p>공고를 조회할 권한이 없습니다. 로그인 상태를 확인해주세요.</p>");
            } else {
                 $(".recruitList").html("<p>공고를 불러오는 중 오류가 발생했습니다.</p>");
            }
        }
    });
});
</script>

</body>
</html>
