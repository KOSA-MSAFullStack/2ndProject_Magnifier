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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        <form id="registerForm" action="${pageContext.request.contextPath}/recruits/register" method="post">
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
            
            <!-- CSRF 토큰 -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-actions">
                <button type="submit">등록하기</button>
            </div>
        </form>
    </div>

<script>
$(document).ready(function() {
    // 폼(id='registerForm') 제출 이벤트 리스너
    $('#registerForm').on('submit', function(event) {
        event.preventDefault();

        // 폼 데이터 수집, JSON 객체 생성
        const formData = {
            title: $('input[name="title"]').val(),
            content: $('textarea[name="content"]').val(),
            careerCondition: $('input[name="careerCondition"]').val(),
            education: $('input[name="education"]').val(),
            employmentType: $('input[name="employmentType"]').val(),
            headCount: $('input[name="headCount"]').val(),
            workingArea: $('input[name="workingArea"]').val(),
            salaryCondition: $('input[name="salaryCondition"]').val(),
            workingHours: $('input[name="workingHours"]').val(),
            workingType: $('input[name="workingType"]').val(),
            insurance: $('input[name="insurance"]').val(),
            retirementSalary: $('input[name="retirementSalary"]').val(),
            deadLine: $('input[name="deadLine"]').val(),
            step: $('input[name="step"]').val(),
            contact: $('input[name="contact"]').val()
        };

        // jQuery를 사용한 Ajax 요청
        $.ajax({
            /*
             * pageContext.request.contextPath:
             * - 웹 컨텍스트 경로(Context Path) 동적 반환
             * - ex: if, http://localhost:8080/my-app 에서 실행된다면, 이 값은 "/my-app"
             * - 서버 환경 바뀌어도 URL 경로 깨지지 않고 유지
            */
            url: '${pageContext.request.contextPath}/recruits/api/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),     // JavaScript 객체 -> JSON 문자열로 변환
            headers: {
                'X-CSRF-TOKEN': '${_csrf.token}'    // CSRF 공격 방지 위한 토큰 추가
            },
            // Ajax 요청 성공 시
            success: function(response) {
                alert('채용 공고가 성공적으로 등록되었습니다.');
                // 공고 목록 페이지로 이동
                window.location.href = '${pageContext.request.contextPath}/recruits/listbyid';
            },
            // Ajax 요청 실패 시
            error: function(xhr, status, error) {
                alert('공고 등록에 실패했습니다: ' + xhr.responseText);
                console.error('Error:', error);
            }
        });
    });
});
</script>

</body>
</html>
