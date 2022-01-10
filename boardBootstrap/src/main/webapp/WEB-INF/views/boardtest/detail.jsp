<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" type="text/css" href="../resources/css/bootstrap.css">
</head>
<body>
	<div class="container">
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
	</div>
	    
    <div class="text-center">
    	<a href="/b/boardtest/list" class="btn btn-outline-secondary">목록으로</a>
    	<a href="/b/boardtest/modify?bno=${detail.bno}" class="btn btn-outline-secondary">수정</a>
    	<a href="/b/boardtest/remove?bno=${detail.bno}" class="btn btn-outline-secondary">삭제</a>
    </div>
</body>
</html>