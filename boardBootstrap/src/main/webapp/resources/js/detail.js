//jquery문법 밖에 작성하고 실행하는 것만 jquery에 작성한다.
let replyService=(function(){//replyService 함수 선언
	//댓글쓰기를 하기 위한 함수 선언
	function add(reply,callback){//add인수가 2개이므로 매개변수도 2개이다.
		//console.log("reply")
		$.ajax({
			url:"/replies/new",//form태그 대신
			type:"post",
			data:JSON.stringify(reply), //JSON.stringify : 자바스크립트의 값을 JSON문자열로 변환
			contentType:"application/json;charset=utf-8",//한글 깨짐 방지
			success:function(result){//통신이 정상적으로 성공했으면 //실제로는 result가 움직이므로
				//callback함수 선언
				//만약 callback이 있으면
				if(callback)
				//callback함수를 호출
					callback(result);//위의 add로 간다.
			},
			error:function(){//통신이 비정상적으로 처리가 되어 error가 있으면
			}
		})
	}
	//댓글 목록리스트를하기 위한 함수 선언
	function getList(param,callback){//타입이 get이면 post방식보다 축약해서 쓸 수 있다.
		let bno=param.bno;
		console.log(bno);
		//$.getJOSN(url+data) //특정 번호를 클릭하면 특정 번호의 댓글이 나올 수 있도록 bno데이터 값을 받아와야 한다.
		$.getJSON(
				"/replies/list/"+bno+".json", //url주소에 bno를 작성되어서 간다./replies/list/선택한bno
				function(data){
					if(callback)
						callback(data);
				})//success 
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
			success:function(result){//통신이 정상적으로 성공했으면 //실제로는 result가 움직이므로
				//callback함수 선언
				//만약 callback이 있으면
				if(callback)
				//callback함수를 호출
					callback(result);//위의 add로 간다.
			},
			error:function(){//통신이 비정상적으로 처리가 되어 error가 있으면
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
			success:function(result){//통신이 정상적으로 성공했으면 //실제로는 result가 움직이므로
				//callback함수 선언
				//만약 callback이 있으면
				if(callback)
				//callback함수를 호출
					callback(result);//위의 add로 간다.
			},
			error:function(){//통신이 비정상적으로 처리가 되어 error가 있으면
			}
		})
	}
	
	
	//return {name:"AAAA"};
	return {//메모리 관리를 위해서 작성
		add:add,//add라는 함수를 add변수에 담아 움직이겠다.
		getList:getList,//getList라는 함수를 getList변수에 담아 움직이겠다.
		reDetail:reDetail,
		reupdate:reupdate,
		remove:remove
	};
})()//익명함수

//=======================================================================================================

$(document).ready(function(){
//	// 상세페이지가 실행되면 댓글 글쓰기 버튼 활성화
//	$("#modalRegisterBtn").show();
//	// 상세페이지가 실행되면 댓글 수정 버튼 활성화
//	$("#modalModBtn").show();
//	// 상세페이지가 실행되면 댓글 삭제 버튼 활성화
//	$("#modalRemoveBtn").show();
	$(".textbox").modal("hide");

	
	// 댓글쓰기 버튼을 클릭하면
	$("#addReplyBtn").on("click",function(){
		//alert("aa")
		//모달창을 띄워라
		$(".textbox").modal("show");
		//Replyer input 내용 초기화
		$("input[name='replyer']").val("")
		//Reply input 내용 초기화
		$("input[name='reply']").val("")
		// 상세페이지가 실행되면 댓글 글쓰기 버튼 활성화
		$("#modalRegisterBtn").show();
		// 댓글 수정 버튼 비활성화
		$("#modalModBtn").hide();
		//댓글 삭제 버튼 비활성화
		$("#modalRemoveBtn").hide();
	});
	
	$(".btn-close").on("click",function(){
		$(".textbox").modal("hide");
	})
	//bno값
	let bno = $("#bno").html();//시작하자마자 들고와야함
	//console.log(bno)
	
	showList();//호출부 detail.jsp가 실행되자마자 댓글목록리스트가 실행되어야 한다.
	
//	detail.jsp가 실행되자 마자
//	댓글목록리스트가 출력(showList호출)
//
//	댓글쓰기를 통해 댓글쓰기가 완성이 되면
//	댓글목록리스트가 출력(showList호출)
//
//	댓글수정를 통해 댓글수정가 완성이 되면
//	댓글목록리스트가 출력(showList호출)
//
//	댓글삭제를 통해 댓글삭제가 완성이 되면
//	댓글목록리스트가 출력(showList호출)
	
	//반복작업시 함수를 만들면 코드도 간결하고 유지보수 할 때 편하게 할 수 있다.//댓글목록리스트
	function showList(){//선언부
		//detail.jsp가 실행될 때 댓글목록리스트가 실행되어야 함.
						//     param  ,   callback	
		replyService.getList({bno:bno},function(list){//변수명:값->이게 param에 들어감 //$.getJOSN의 bno, function
		
			console.log(list);
			/*$("#relist").html(list[0].reply)
			$("#relist").html(list[1].reply)
			$("#relist").html(list[2].reply)-> 덮어쓰기 된다.*/
			
			let str="";
			for(let i=0; i<list.length; i++){
				str+="<li data-rno='"+list[i].rno+ "'><div><b>"+list[i].replyer+"</b></div>"
				str+=" "+list[i].reply+"</div>"
				str+="</li>"
			}
			
			$("#relist").html(str)
			
		});
	}
	
	//console.log(replyService);//replyService 함수 호출
	//댓글쓰기 버튼(id값이 modalRegisterBtn)을 클릭하면
	$("#modalRegisterBtn").on("click",function(){
		//alert("aa")
		//console.log($("#aaa").val());
		//replyService.add({replyer:"정자바",reply:"2번 게시판에 대한 댓글쓰기",bno:bno});//인수     자바문법을 자바스크립트 문법으로 바꿈:json
		
		//사용자가 입력한 댓글내용을 저장
		let reply = $("input[name='reply']").val()//클릭할 때 나타나야함
		//사용자가 입력한 댓글작성자를 저장
		let replyer = $("input[name='replyer']").val()//클릭할 때 나타나야함
		
		// 글쓰기 버튼 활성화
		//$("#modalRegisterBtn").show();
		
		//							ajax로 보내고자하는 json타입	  , 				
		replyService.add({replyer:replyer,reply:reply,bno:bno}, 
				function(result){//callback 함수 호출 //insert하고 후 조치가 필요한 경우 callback에 적용시킨다(redirect와 비슷하다.)
					alert("insert 작업: "+result)
					//목록리스트 처리
					showList();	
				}
		);//add의 인수 ({replyer:replyer,reply:reply,bno:bno},function(result){})로 2개이다.
		//모달창을 숨겨라
		$(".modal").modal("hide");
	})//모달창 안에 댓글 쓰기
	
	//댓글 내용을 클릭하면(수정 및 삭제를 하기 위해서)
	$("#relist").on("click","li", function(){//이벤트 델리게이션 : 범위 한정시키기 
		
		//rno값을 가져오기
		let rno=$(this).data("rno");//li가 하나만 있는 것이 아니라 여러개가 있다. 그래서 li를 작성하는 것이 아니라 내가 선택한 li에 있는 rno값 this를 작성한다.
		
		replyService.reDetail(rno,function(detail){
			console.log(detail.reply)
			console.log(detail.replyer)
			
			$("input[name='rno']").val(detail.rno)
			$("input[name='replyer']").val(detail.replyer)
			$("input[name='reply']").val(detail.reply)
			
			//글쓰기 버튼 비활성화
			$("#modalRegisterBtn").hide();
			//글수정 버튼 활성화
			$("#modalModBtn").show();
			//글삭제 버튼 활성화
			$("#modalRemoveBtn").show();
			//modal창을 띄워라.
			$(".modal").modal("show");
		})
	})
	
	//댓글 수정버튼을 클릭하면
	$("#modalModBtn").on("click",function(){
		//alert("aa")
		//var rno= $("input[name='rno'").val()
		var reply={rno:$("input[name='rno']").val(),reply:$("input[name='reply']").val()}//선언한 곳이 json타입이기 때문에
		console.log(reply)
		//댓글 수정함수를 호출해서 처리(<-댓글 수정을 하기 위한 함수 선언)
		replyService.reupdate(reply,function(update){// callback -> 익명함수
			//callback영역 //update가 정상적으로 처리된 후 조치
			alert("update 작업: "+update)
			//modal창을 숨겨라
			$(".modal").modal("hide");
			
			//목록리스트 를 실행
			showList();	
		})
	})
	
	//댓글 삭제버튼을 클릭하면
	$("#modalRemoveBtn").on("click",function(){
//		alert("aa")
		var reply={rno:$("input[name='rno']").val()}//선언한 곳이 json타입이기 때문에
//		console.log(rno)
		//댓글 삭제함수를 호출해서 처리(<-댓글 삭제을 하기 위한 함수 선언)
		replyService.remove(reply,function(remove){// callback -> 익명함수
			//callback영역 //delete가 정상적으로 처리된 후 조치
			alert("delete 작업: "+remove)
			//modal창을 숨겨라
			$(".modal").modal("hide");
			
			//목록리스트 를 실행
			showList();	
		})
	})
	
})//jquery끝

