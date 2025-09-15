<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!-- * author: 김기성 -->
<!-- 개인회원 -->
<c:set var="isMember" value="false" />
<security:authorize access="isAuthenticated() and hasRole('ROLE_MEMBER')">
    <c:set var="isMember" value="true" />
</security:authorize>
<!-- 기업회원 -->
<c:set var="isEnterprise" value="false" />
<security:authorize access="isAuthenticated() and hasRole('ROLE_ENTERPRISE')">
    <c:set var="isEnterprise" value="true" />
</security:authorize>
<!-- 일반사용자(비로그인) -->
<c:set var="isAnonymous" value="true" />
<security:authorize access="isAuthenticated()">
    <c:set var="isAnonymous" value="false" />
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
    <style>
      .pagination .page-ellipsis {
        display: inline-block;
        padding: 8px 12px;
        margin: 0 2px;
        color: #333;
      }
      .pagination-progress {
        width: 100%;
        background-color: #eee;
        border-radius: 5px;
        margin: 20px 0 10px 0;
        height: 10px;
      }
      .progress-bar {
        width: 0%; /* Will be set by JS */
        height: 100%;
        background-color: #007bff;
        border-radius: 5px;
        transition: width 0.3s ease-in-out;
      }
    </style>
  </head>
  <body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="main">
      <div class="recruitList">
        <!-- 공고 목록이 동적으로 여기에 추가됩니다. -->
      </div>
      <div class="pagination-progress">
        <div class="progress-bar"></div>
      </div>
      <div class="pagination">
        <!-- 페이지네이션이 동적으로 여기에 추가됩니다. -->
      </div>
    </div>

    <script>
      const isMemberJs = "${isMember}" === "true";
      const isEnterpriseJs = "${isEnterprise}" === "true";
      const isAnonymousJs = "${isAnonymous}" === "true";

      function renderPagination(currentPage, totalPages, container) {
        container.empty();
        
        // Update progress bar
        const progressPercent = totalPages > 1 ? ((currentPage -1) / (totalPages -1)) * 100 : 0;
        $(".progress-bar").css("width", progressPercent + "%");

        if (totalPages <= 1) {
          if (totalPages === 1) {
            container.append('<a href="#" class="page-link active" data-page="1">1</a>');
          }
          return;
        }

        const blockSize = 10;
        const currentBlock = Math.floor((currentPage - 1) / blockSize);
        const startPage = currentBlock * blockSize + 1;
        const endPage = Math.min(startPage + blockSize - 1, totalPages);

        // '처음' (First page) and '이전' (Previous block) buttons
        if (startPage > 1) {
            container.append('<a href="#" class="page-link" data-page="1" title="처음">&laquo;</a>');
            const prevBlockPage = startPage - 1;
            container.append('<a href="#" class="page-link" data-page="' + prevBlockPage + '" title="이전">&lsaquo;</a>');
        }

        // Page numbers in the current block
        for (let i = startPage; i <= endPage; i++) {
            const activeClass = i === currentPage ? "active" : "";
            container.append('<a href="#" class="page-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
        }

        // '다음' (Next block) button
        if (endPage < totalPages) {
            const nextBlockPage = endPage + 1;
            container.append('<a href="#" class="page-link" data-page="' + nextBlockPage + '" title="다음">&rsaquo;</a>');
        }

        // '끝' (Last page) button
        if (endPage < totalPages) { // Only show if not in the last block of pages
            container.append('<a href="#" class="page-link" data-page="' + totalPages + '" title="끝">&raquo;</a>');
        }
      }

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
          // Clear progress bar if no results
          $(".progress-bar").css("width", "0%");
          return;
        }

        // 각 공고에 대한 HTML 생성, 컨테이너에 추가
        recruits.forEach(function (recruit) {
          const detailUrl = '${pageContext.request.contextPath}/recruits/detail/' + recruit.recruitId;

          let applyButtonHtml = "";
          if (!isEnterpriseJs) {
            applyButtonHtml = '<a href="' + detailUrl + '" class="applyButton">지원하기</a>';
          }

          const recruitHtml =
            '<div class="recruit">' +
                '<span class="title">' + recruit.title + '</span>' +
                '<span class="details">' + recruit.workingArea + ' | ' + recruit.enterpriseName + '</span>' +
                '<div class="apply">' +
                    applyButtonHtml +
                    '<span class="deadline">마감일 : ' + recruit.deadLine + '</span>' +
                '</div>' +
            '</div>';
          recruitListContainer.append(recruitHtml);
        });

        // 페이지네이션 HTML 생성
        renderPagination(currentPage, totalPages, paginationContainer);
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