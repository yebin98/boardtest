/**
 * 
 */

$(document).ready(function(){
	//상세페이지가 시작되자마자 이미지를 출력하기위한 ajax
	$.getJSON("/b/boardtest/fileList/"+bno+".json",
		function(data){
			var str="";
			//detail.jsp에 뿌리기
			$(data).each(function(i,obj){
				console.log(data)
				if(!obj.image){
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
					str+="<li data-path='"+obj.uploadPath+"'";
					str+="data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'>"; 
					str+="<a href='/b/download?fileName="+fileCallPath+"'>"+obj.fileName+"</a></li>"
				}else{
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
					console.log(fileCallPath)

					str+="<li data-path='"+obj.uploadPath+"'";
					str+="data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'>"; 
					str+="<img src='/b/display?fileName="+fileCallPath+"'></li>"
				}
			})
			$("#uploadResult ul").html(str)
		})
	
	
	
	
	
	$("#modify").on("click", function(){
		alert("수정 완료")
	})
})