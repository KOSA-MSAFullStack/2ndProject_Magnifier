<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="isMember" value="false" />
<security:authorize access="isAuthenticated() and hasRole('ROLE_MEMBER')">
    <c:set var="isMember" value="true" />
</security:authorize>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>채용공고</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/common.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/recruit.css"
    />
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
        <input type="text" placeholder="찾고있는 공고를 검색해주세요." />
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
      const isMemberJs = "${isMember}" === "true";

      // 공고 목록 & 페이지네이션 화면에 표시
      function displayRecruits(recruits, currentPage, totalPages, totalCount) {
        const recruitListContainer = $(".recruitList");
        const paginationContainer = $(".pagination");

        // 기존 목록 & 페이지네이션 비움
        recruitListContainer.empty();
        paginationContainer.empty();

        // 데이터 x or 비어있는 경우 메시지 표시
        if (!recruits || recruits.length === 0) {
          recruitListContainer.html("<p>등록된 채용 공고가 없습니다.</p>");
          return;
        }

        // 각 공고에 대한 HTML 생성, 컨테이너에 추가
        recruits.forEach(function (recruit) {
          const applyButtonHtml = isMemberJs
            ? `<a href="${pageContext.request.contextPath}/recruits/detail/\${recruit.recruitId}" class="applyButton">지원하기</a>`
            : "";
          const recruitHtml = `
            <div class="recruit">
                <span class="title">\${recruit.title}</span>
                <span class="details">\${recruit.workingArea}</span>
                <div class="apply">
                    \${applyButtonHtml}
                    <span class="deadline">마감일 : \${recruit.deadLine}</span>
                </div>
            </div>
        `;
          recruitListContainer.append(recruitHtml);
        });

        // 페이지네이션 HTML 생성
        if (currentPage > 1) {
          paginationContainer.append(
            `<a href="#" class="page-link" data-page="\${currentPage - 1}">이전</a>`
          );
        }

        for (let i = 1; i <= totalPages; i++) {
          let activeClass = i === currentPage ? "active" : "";
          paginationContainer.append(
            `<a href="#" class="page-link \${activeClass}" data-page="\${i}">\${i}</a>`
          );
        }

        if (currentPage < totalPages) {
          paginationContainer.append(
            `<a href="#" class="page-link" data-page="\${currentPage + 1}">다음</a>`
          );
        }
      }

      // 페이지 번호를 인자로 받아 해당 페이지의 공고 목록을 API에서 불러오는 함수
      function fetchRecruits(page) {
        $.ajax({
          url: "${pageContext.request.contextPath}/recruits/api/list", // API 엔드포인트 호출
          type: "GET",
          data: { page: page, size: 10 }, // 한 페이지에 10개씩
          dataType: "json",
          success: function (response) {
            displayRecruits(
              response.recruits,
              response.currentPage,
              response.totalPages,
              response.totalCount
            );
          },
          error: function (xhr, status, error) {
            console.error("공고 목록을 불러오는 중 오류 발생: ", error);
            $(".recruitList").html(
              "<p>공고를 불러오는 중 오류가 발생했습니다.</p>"
            );
          },
        });
      }

      $(document).ready(function () {
        // 페이지 처음 로드될 때 1페이지 로드
        fetchRecruits(1);

        // 페이지네이션 링크 클릭 이벤트 위임
        $(".pagination").on("click", ".page-link", function (e) {
          e.preventDefault();
          const page = $(this).data("page");
          fetchRecruits(page);
        });
      });
    </script>
  </body>
</html>
