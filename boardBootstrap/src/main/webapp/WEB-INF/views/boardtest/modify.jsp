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
	<div class=" mb-2 p-3 bg-secondary text-white text-center">수정내용 입력</div>
	<form action="/b/boardtest/modify" method="post">
		<div class="container">
			<div>
		        <input type="hidden" value="${detail.bno}" name="bno">
		    </div>
		    <div class="bg-secondary text-white text-center">
		    	<input type="text" value="${detail.title}" name="title" 
		    			class="mb-2 bg-secondary text-white text-center" style="border: none">
		    </div>
			<div class="row align-items-start" style="margin: 0">
			    <div class="col bg-secondary p-2 text-dark bg-opacity-50">작성자</div>
			    <input type="text" value="${detail.writer}" name="writer" class="col" style="border: none">
			    <div class="col bg-secondary p-2 text-dark bg-opacity-50">조회수</div>
			    <div class="col">${detail.cnt}</div>
			</div>
			<div>
				<textarea name="content" class="form-control" id="floatingTextarea2" style="width: 100%; height: 300px">${detail.content}</textarea>
	    	</div>
		</div>
	    <div class="text-center">
	    	<a href="/b/boardtest/list" class="btn btn-outline-secondary">목록으로</a>
		   <input type="submit" value="수정" class="btn btn-outline-secondary">
		</div>
	</form>
</body>
</html>