/**
 * 
 */
$(document).ready(function(){
	let actionForm = $("#actionForm")
	let actionForm1 = $("#actionForm1")
	
	// 현재페이지 번호를 클릭하면
	$(".paginate_button a").on("click",function(e){
		//alert("aaa")
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"))
		actionForm.submit();
	})
	
	//검색버튼을 클릭하면
	$("#search").on("click", function(e){
		e.preventDefault();
		//pageNum의 값을 1로 설정
		actionForm.find("input[name='pageNum']").val("1")
		actionForm.submit();
	})
	
	// 글쓰기 버튼을 클릭하면
	$("#writeList").on("click",function(e){
		//alert("aaa")
		e.preventDefault();
		actionForm1.attr("href")
		actionForm1.submit();
	})
	
	
})
