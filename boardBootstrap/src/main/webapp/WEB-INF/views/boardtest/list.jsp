<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" type="text/css" href="../resources/css/bootstrap.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../resources/js/list.js"></script>
</head>
<body>
	<h1 class="text-center">게시판</h1>
	<form id="actionForm" action="/b/boardtest/list" method="get">
		<div class="form-control border-0 small position-relative" style="height:50px">
        	<div class="position-absolute bottom-0 end-0">
        		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
	        	<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
	            <select name="search" id="">
	             		<option value="t"<c:if test="${pageMaker.cri.search=='t'}"> selected </c:if>>제목</option>
	             		<option value="c"<c:if test="${pageMaker.cri.search=='c'}"> selected </c:if>>내용</option>
	             		<option value="w"<c:if test="${pageMaker.cri.search=='w'}"> selected </c:if>>작성자</option>
	            		<option value="tc"<c:if test="${pageMaker.cri.search=='tc'}"> selected </c:if>>제목 + 내용</option>
	             		<option value="tcw"<c:if test="${pageMaker.cri.search=='tcw'}"> selected </c:if>>제목 + 내용 + 작성자</option>
	          	</select>
	          	<input type="text" name="keyword" value="${pageMaker.cri.keyword}">
	          	<input type="submit" value="검색" class="btn btn-secondary opacity-75">
        	</div>
        </div>
	</form>
	<form action="/b/boardtest/write">
		<table class="table table-striped">
			<thead>
			    <tr>
			      <th scope="col">번호</th>
			      <th scope="col">제목</th>
			      <th scope="col">작성자</th>
			      <th scope="col">날짜</th>
			      <th scope="col">조회수</th>
			    </tr>
		  	</thead>
			<tbody>
		      	<c:forEach items="${list}" var="board"><!-- var = "변수명" -->
		      		<tr>
		               	<td>${board.bno}</td>
		              	<td>
		              		<a href="/b/boardtest/detail?bno=${board.bno}" style="color:#333; text-decoration:none">${board.title}</a>
		              	</td>
		               	<td>${board.writer}</td>
		               	<td>${board.regdate}</td>
		               	<td>${board.cnt}</td>
           			</tr>
      			</c:forEach>
      		</tbody>
		</table>
		<div class="text-end">
		   <input type="submit" value="글쓰기" class="btn btn-outline-secondary">
		</div>
	</form>
	<form id="actionForm" action="/b/boardtest/list" method="get">
        <div id="dataTable_paginate" aria-label="Page navigation example">
        	<ul class="pagination justify-content-center">
        		<c:if test="${pageMaker.prev}">
        			<li class="paginate_button page-item previous" id="dataTable_previous">
	        			<a href="${pageMaker.startPage-1}" class="page-link text-muted">prev</a>
	        		</li>
        		</c:if>
	        	<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}"><!-- var = 변수명 -->
	        		<li class="paginate_button page-item ">
	        			<a href="${num}" class="page-link text-muted">${num}</a>
	        			<!-- <a href="/board/list?pageNum=${num}&amount=${pageMaker.cri.amount}" class="page-link">${num}</a> -->
	        		</li>
	        	</c:forEach>
	        	<c:if test="${pageMaker.next}">
        			<li class="paginate_button page-item next" id="dataTable_next">
		        		<a href="${pageMaker.endPage+1}" class="page-link text-muted">next</a>
		        	</li>
        		</c:if>
        	</ul>
        </div>
    </form>
</body>
</html>