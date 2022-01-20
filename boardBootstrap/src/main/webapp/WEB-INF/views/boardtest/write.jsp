<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel = "stylesheet" type="text/css" href="../resources/css/bootstrap.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../resources/js/uploadAjax.js"></script>
</head>
<body>
	<div class="p-3 bg-secondary text-white text-center">글쓰기</div>
	<form action="/b/boardtest/write" method="post" role="form">
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
     	<div>
        	<input type="file" name="uploadFile" multiple>
    	</div>
       	<div class="text-center">
		   <input type="submit" value="작성" class="btn btn-outline-secondary">
		</div>
	</form>
	<div id="uploadResult">
   		<ul></ul>
   	</div>
	<div class="text-end">
		<a href="/b/boardtest/list" class="btn btn-outline-secondary">목록으로</a>
	</div>
	
	<%-- <% 
	 document.getElementById("write").onclick = function() {
        alert("저장 완료");
    };
	%> --%>
</body>
</html>