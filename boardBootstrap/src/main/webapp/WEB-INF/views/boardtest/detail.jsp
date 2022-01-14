<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel = "stylesheet" type="text/css" href="../resources/css/bootstrap.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../resources/js/detail.js"></script>
	<script type="text/javascript" src="../resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="../resources/js/metisMenu.js"></script>
	<script type="text/javascript" src="../resources/js/sb-admin-2.js"></script>
	
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
			<div class="form-control" id="floatingTextarea2" style="width: 100%; height: 300px">${detail.content}</div>
	    </div>
		<div class="text-center">
	    	<a href="/b/boardtest/list" class="btn btn-outline-secondary">목록으로</a>
	    	<a href="/b/boardtest/modify?bno=${detail.bno}" class="btn btn-outline-secondary">수정</a>
	    	<a href="/b/boardtest/remove?bno=${detail.bno}" class="btn btn-outline-secondary">삭제</a>
	    </div>
	    <div class="panel-body">
            <div>
            	<ul id="relist"></ul>
            </div>
	      	<button class="btn btn-outline-secondary" id="addReplyBtn" data-toggle="modal" data-target="#myModal">
               	댓글 쓰기
            </button>
            <div class="textbox">
		        <div>
		            <h4 class="modal-title" id="myModalLabel">댓글</h4>
		            <div>
		                <input type="hidden" name="rno" class="form-control">
		            </div>
		            <div>
		                <label>작성자</label>
		                <input type="text" name="replyer" class="form-control">
		            </div>
		            <div>
		                <label>내용</label>
		                <textarea class="form-control" name="" id="" cols="30" rows="10"></textarea>
		            </div>
		        </div>
		        <div class="modal-footer">
		            <button type="button" class="btn btn-outline-secondary" id="modalRegisterBtn">댓글쓰기</button>
		            <button type="button" class="btn btn-outline-secondary" id="modalModBtn">댓글수정</button>
		            <button type="button" class="btn btn-outline-secondary" id="modalRemoveBtn">댓글삭제</button>
		           <button type="button" class="btn-close" aria-label="Close"></button>
		        </div>
		    </div>
	    </div>
	</div>
</body>
</html>