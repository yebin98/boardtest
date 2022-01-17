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
	<script type="text/javascript" src="../resources/js/detail.js"></script>
	<script type="text/javascript" src="../resources/js/bootstrap.js"></script>
	
</head>
<body>
	<div class="container">
		<div id="bno" class="p-3 bg-secondary text-white text-center opacity-50">${detail.bno}</div>
		<div class="p-3 bg-secondary text-white text-center">${detail.title}</div>
		<div class="row align-items-start" style="margin: 0">
		    <div class="col bg-secondary p-2 text-dark bg-opacity-50">작성자</div>
		   	<div class="col">${detail.writer}</div>
		    <div class="col bg-secondary p-2 text-dark bg-opacity-50">조회수</div>
		    <div class="col">${detail.cnt}</div>
		</div>
		<div>
			<div class="form-control" id="floatingTextarea2" style="width: 100%; height: 300px; margin-bottom: 20px">${detail.content}</div>
	    </div>
		<div class="text-end">
	    	<a href="/b/boardtest/list" class="btn btn-outline-secondary">목록으로</a>
	    	<a href="/b/boardtest/modify?bno=${detail.bno}" class="btn btn-outline-secondary">수정</a>
	    	<a href="/b/boardtest/remove?bno=${detail.bno}" class="btn btn-outline-secondary" id="remove">삭제</a>
	    </div>
	    <div class="panel-body">
	    	<h4>댓글</h4>
            <div>
            	<ul id="relist"></ul>
            </div>
            <%-- <ul id="relist">
            	<c:forEach items="${getList}" var="board">
            		<li>${board.replyer}</li>
	            	<li>${board.reply}</li>
            	</c:forEach>
            </ul> --%>
    		
	      	<button class="btn btn-outline-secondary" id="addReplyBtn" data-toggle="modal" data-target="#myModal" style="margin-bottom: 20px">
               	댓글쓰기
            </button>
            <div id="textbox">
		        <div style="margin-bottom: 20px">
		            <div>
		                <input type="hidden" name="rno" class="form-control">
		            </div>
		            <div>
		                <label>작성자</label>
		                <input type="text" name="replyer" class="form-control">
		            </div>
		            <div>
		                <label>내용</label>
		                <textarea class="form-control" name="reply" id="reply" cols="10" rows="10"></textarea>
		            </div>
		        </div>
		        <div class="text-end" style="margin-bottom: 20px">
		            <button type="button" class="btn btn-outline-secondary" id="modalRegisterBtn">저장</button>
		            <button type="button" class="btn btn-outline-secondary" id="modalModBtn">수정</button>
		            <button type="button" class="btn btn-outline-secondary" id="modalRemoveBtn">삭제</button>
		           	<button type="button" class="btn btn-outline-secondary" id="btn-close" aria-label="Close">닫기</button>
		        </div>
		    </div>
	    </div>
	</div>
</body>
</html>