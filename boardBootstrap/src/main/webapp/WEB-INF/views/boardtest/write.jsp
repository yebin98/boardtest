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
	<div class="p-3 bg-secondary text-white text-center">글쓰기</div>
	<form action="/b/boardtest/write" method="post">
		<table class="table">
	    	<tr>
	    		<th scope="col" style="width:80px">
	    			<label for="formGroupExampleInput" class="form-label">작성자</label>
	    		</th>
	            <td>
	            	<input type="text" name="writer">
	            </td>
	    	</tr>
	    	<tr>
	    		<th scope="col" style="width:80px">
	    			<label for="formGroupExampleInput" class="form-label">제목</label>
	    		</th>
	            <td>
	            	<input type="text" name="title" style="width:600px">
	            </td>
	    	</tr>
	    	<tr>
	    		<th scope="col" style="width:80px">
	    			<label for="formGroupExampleInput" class="form-label">내용</label>
	    		</th>
	            <td>
	            	<textarea name="content" class="form-control" id="floatingTextarea2" style="width: 100%; height: 300px"></textarea>
	            </td>
	    	</tr>
       	</table>
       	<div class="text-center">
		   <input type="submit" value="작성" class="btn btn-outline-secondary">
		</div>
	</form>
</body>
</html>