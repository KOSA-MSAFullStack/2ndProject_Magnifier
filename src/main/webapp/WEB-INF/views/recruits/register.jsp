<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용공고 등록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/recruit.css">
    <style>
        .register-form-container {
            width: 80%;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .register-form-container h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        .form-table {
            width: 100%;
            border-collapse: collapse;
        }
        .form-table th, .form-table td {
            padding: 15px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .form-table th {
            background-color: #f9f9f9;
            width: 150px;
            font-weight: bold;
        }
        .form-table input[type="text"], .form-table input[type="date"], .form-table textarea {
            width: 95%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-table textarea {
            height: 150px;
            resize: vertical;
        }
        .form-actions {
            text-align: center;
            margin-top: 30px;
        }
        .form-actions button {
            padding: 10px 20px;
            font-size: 22px;
            background-color: var(--darkblue);
            color: var(--white);
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-actions button:hover {
            background-color: var(--blue);
        }
    </style>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="register-form-container">
        <h1>채용공고 등록</h1>
        <form action="${pageContext.request.contextPath}/recruits/register" method="post">
            <table class="form-table">
                <tr>
                    <th>공고 제목</th>
                    <td colspan="3"><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th colspan="4">모집 요강</th>
                </tr>
                <tr>
                    <th>직무 내용</th>
                    <td colspan="3"><textarea name="content" required></textarea></td>
                </tr>
                <tr>
                    <th>경력 조건</th>
                    <td><input type="text" name="careerCondition"></td>
                    <th>학력</th>
                    <td><input type="text" name="education"></td>
                </tr>
                <tr>
                    <th>고용 형태</th>
                    <td><input type="text" name="employmentType"></td>
                    <th>모집 인원</th>
                    <td><input type="text" name="headCount"></td>
                </tr>
                <tr>
                    <th>근무 지역</th>
                    <td colspan="3"><input type="text" name="workingArea"></td>
                </tr>
                <tr>
                    <th colspan="4">근무 조건</th>
                </tr>
                <tr>
                    <th>임금 조건</th>
                    <td><input type="text" name="salaryCondition"></td>
                    <th>근무 시간</th>
                    <td><input type="text" name="workingHours"></td>
                </tr>
                <tr>
                    <th>근무 형태</th>
                    <td><input type="text" name="workingType"></td>
                    <th>사회 보험</th>
                    <td><input type="text" name="insurance"></td>
                </tr>
                <tr>
                    <th>퇴직 급여</th>
                    <td colspan="3"><input type="text" name="retirementSalary"></td>
                </tr>
                <tr>
                    <th colspan="4">접수 기간 및 방법</th>
                </tr>
                <tr>
                    <th>접수마감일</th>
                    <td><input type="date" name="deadLine" required></td>
                    <th>전형단계</th>
                    <td><input type="text" name="step"></td>
                </tr>
                 <tr>
                    <th colspan="4">위치 정보</th>
                </tr>
                <tr>
                    <th>담당자 연락처</th>
                    <td colspan="3"><input type="text" name="contact"></td>
                </tr>
            </table>
            
            <!-- 현재 로그인한 기업의 ID를 넘겨주기 위한 hidden input -->
            <sec:authentication property="principal.enterprise.enterpriseId" var="enterpriseId"/>
            <input type="hidden" name="enterpriseId" value="${enterpriseId}">
            
            <!-- CSRF 토큰 -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-actions">
                <button type="submit">등록하기</button>
            </div>
        </form>
    </div>
</body>
</html>
