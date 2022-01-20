let replyService=(function(){
	//댓글쓰기를 하기 위한 함수 선언
	function add(reply,callback){
		//sconsole.log("reply")
		$.ajax({
			url:"/replies/new",
			type:"post",
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				if(callback)
					callback(result);
			},
			error:function(){
			}
		})
	}
	//댓글 목록리스트를하기 위한 함수 선언
	function getList(param,callback){
		let bno=param.bno;
		//console.log(bno);
		$.getJSON(
			"/replies/list/"+bno+".json",
			function(data){
				if(callback)
					callback(data);
		})
	}
	
	//댓글 수정을 하기 위해 댓글 내용 가져오는 함수 선언
	function reDetail(rno, callback){
		var rno=rno;
		$.getJSON(
			"/replies/"+rno+".json",
			function(data){
				if(callback)
					callback(data);
		})
	}
	
	//댓글 수정을 하기 위한 함수 선언
	function reupdate(reply,callback){
		$.ajax({
			url:"/replies/update",
			type:"put",
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				if(callback)
					callback(result);
			},
			error:function(){
			}
		})
	}
	
	//댓글 삭제를 하기 위한 함수 선언
	function remove(reply,callback){
		$.ajax({
			url:"/replies/remove",
			type:"delete",
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				if(callback)
					callback(result);
			},
			error:function(){
			}
		})
	}
	
	return {
		add:add,
		getList:getList,
		reDetail:reDetail,
		reupdate:reupdate,
		remove:remove
	};
})()

//===================================================================================================================================

$(document).ready(function(){
	//bno값
	let bno = $("#bno").html();//시작하자마자 들고와야함
	//console.log(bno)
	
	//상세페이지가 시작되자마자 이미지를 출력하기위한 ajax
	$.getJSON("/b/boardtest/fileList/"+bno+".json",
		function(data){
			var str="";
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
	$("#textbox").hide();
	$("#bno").hide();
	
	$("#remove").on("click", function(){
		alert("삭제 완료")
	})

	// 댓글쓰기 버튼을 클릭하면
	$("#addReplyBtn").on("click",function(){
		//alert("aa")
		$("#textbox").show();
		//Replyer input 내용 초기화
		$("input[name='replyer']").val("")
		//Reply input 내용 초기화
		$("#reply").val("")
		// 상세페이지가 실행되면 댓글 글쓰기 버튼 활성화
		$("#modalRegisterBtn").show();
		// 댓글 수정 버튼 비활성화
		$("#modalModBtn").hide();
		//댓글 삭제 버튼 비활성화
		$("#modalRemoveBtn").hide();
	});
	
	$("#btn-close").on("click",function(){
		$("#textbox").hide();
	})
	
	showList();
	
	function showList(){
		replyService.getList({bno:bno},function(list){
			//console.log(list);
			let str="";
			for(let i=0; i<list.length; i++){
				str+="<li data-rno='"+list[i].rno+ "'style='list-style:none; margin-bottom:10px'><div><b>"+list[i].replyer+"</b>"+list[i].replydate+"</div>"
				str+=" "+list[i].reply+"</li>"
			}
			$("#relist").html(str)
		});
	}
	
	//저장 버튼(id값이 modalRegisterBtn)을 클릭하면
	$("#modalRegisterBtn").on("click",function(){
		//alert("aa")
		//console.log($("#aaa").val());
		
		//사용자가 입력한 댓글내용을 저장
		let reply = $("#reply").val()
		//사용자가 입력한 댓글작성자를 저장
		let replyer = $("input[name='replyer']").val()
				
		replyService.add({replyer:replyer,reply:reply,bno:bno}, 
			function(result){
				alert("저장 완료")
				//목록리스트 처리
				showList();	
			}
		);
		//Replyer input 내용 초기화
		$("#reply").val("")
		//Reply input 내용 초기화
		$("#replyer").val("")
	})
	
	//댓글 내용을 클릭하면(수정 및 삭제를 하기 위해서)
	$("#relist").on("click","li", function(){
		$("#textbox").show();
		//rno값을 가져오기
		let rno=$(this).data("rno");
		
		replyService.reDetail(rno,function(detail){
			//console.log(detail.reply)
			//console.log(detail.replyer)
			
			$("input[name='rno']").val(detail.rno)
			$("input[name='replyer']").val(detail.replyer)
			$("#reply").val(detail.reply)
			
			//글쓰기 버튼 비활성화
			$("#modalRegisterBtn").hide();
			//글수정 버튼 활성화
			$("#modalModBtn").show();
			//글삭제 버튼 활성화
			$("#modalRemoveBtn").show();
		})
	})
	
	//댓글 수정버튼을 클릭하면
	$("#modalModBtn").on("click",function(){
		//alert("aa")
		var reply={rno:$("input[name='rno']").val(),reply:$("#reply").val()}
		console.log(reply)
		//댓글 수정함수를 호출해서 처리
		replyService.reupdate(reply,function(update){
			//callback영역
			alert("수정 완료")
			//목록리스트 를 실행
			showList();	
		})
	})
	
	//댓글 삭제버튼을 클릭하면
	$("#modalRemoveBtn").on("click",function(){
//		alert("aa")
		var reply={rno:$("input[name='rno']").val()}
//		console.log(rno)
		//댓글 삭제함수를 호출해서 처리
		replyService.remove(reply,function(remove){
			//callback영역 
			alert("삭제 완료")
			//목록리스트 를 실행
			showList();	
		})
	})
	
})//jquery끝

