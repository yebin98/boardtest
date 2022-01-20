$(document).ready(function(){
	
	// 파일의 크기와 확장자를 검사하는 함수
	
	// 서버에서 설정해 놓은 파일크기 설정
	let maxSize=5242880;//5MB
	// 서버에서 허용 불가능한 확장자 설정(정규식) : 규칙(파일 검증)
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	
	function checkExtension(fileSize,fileName){
		//사용자가 선택한 파일의 크기가 서버에서 설정해 놓은 파일의 크기보다 이상이면,
		if(fileSize>=maxSize){
			alert("파일 사이즈 초과")
			return false;
		}
		//사용자가 선택한 파일의 확장자가 서버에서 설정해 놓은 화질의 확장자와 일치하지 않으면,
		if(regex.test(fileName)){
			//실행하지 마라
			alert("해당 종류의 파일은 업로드 할 수 없습니다.")
			return false;
		}
		return true;
	}
	let formObj = $("form[role='form']")
	//글쓰기 버튼을 클릭하면
	$("input[type='submit']").on("click",function(e){
		e.preventDefault();
		var str="";
		$("#uploadResult ul li").each(function(i,obj){
			console.log(obj);
			str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+$(obj).data("filename")+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+$(obj).data("uuid")+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+$(obj).data("path")+"'>"
			str+="<input type='hidden' name='attachList["+i+"].image' value='"+$(obj).data("type")+"'>"
		})
		formObj.append(str).submit();
	})
	//파일 선택의 내용이 변경되면
	$("input[type='file']").on("change", function(e){
//		alert("aaa")	
		let formData = new FormData();
		let inputFile=$("input[name='uploadFile']");
		let files=inputFile[0].files;
		console.log(files);

		e.preventDefault();
		//console.log("aa");
		for(let i=0; i<files.length; i++){
			if(!checkExtension(files[i].size,files[i].name)){
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
		
		//ajax를 이용해서 컨트롤러 연결
		$.ajax({
			url:"/b/uploadAjaxAction",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				alert("upload성공")
				showUploadedFile(result);
			}
		})//$.ajax끝
	})
})//$(document).ready(function(){ 끝

//사용자가 선택한 파일을 원하는 경로에 성공적으로 업로드 한 후 웹브라우저에 파일을 띄워라에 대한 함수 선언(showUploadedFile)
function showUploadedFile(uploadresultArr){
	var str="";
	$(uploadresultArr).each(function(i,obj){//each : 제이쿼리 for문
		console.log(obj)
		if(!obj.image){//사용자가 업로드한 파일의 타입이 이미지가 아니면(excel파일, ppt파일)
			var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
			str+="<li data-path='"+obj.uploadPath+"'";
			str+="data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'>"; 
			str+="<a href='/b/download?fileName="+fileCallPath+"'>"+obj.fileName+"</a></li>"
		}else{//사용자가 업로드한 파일의 타입이 이미지이면(.jpg .png .gif)
			var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);//\ 표시를 /로 바꿔줌
			console.log(fileCallPath)
			//img태그를 사용해서 웹브라우저 이미지 출력
			str+="<li data-path='"+obj.uploadPath+"'";
			str+="data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'>"; 
			str+="<img src='/b/display?fileName="+fileCallPath+"'></li>"
		}
	})
	$("#uploadResult ul").html(str)
}