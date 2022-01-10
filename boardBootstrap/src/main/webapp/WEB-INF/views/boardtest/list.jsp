<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" type="text/css" href="../resources/css/bootstrap.css">
</head>
<body>
	<h1 class="text-center">게시판</h1>
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
		<div class="text-center">
		   <input type="submit" value="글쓰기" class="btn btn-outline-secondary">
		</div>
	</form>
</body>
</html>