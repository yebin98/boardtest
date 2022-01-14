/**
 * 폼테그의 serch와 keyword를 가지고 와라 
 */
$(document).ready(function(){
	var actionForm = $("#actionForm")
	// 현재페이지 번호를 클릭하면
	$(".paginate_button a").on("click",function(e){
		//alert("aaa")
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"))
		// actionForm.find("input[name='pageNum']") = <input type="text" name="pageNum" value="${pageMaker.cri.pageNum}"> 
		// val  = ${pageMaker.cri.pageNum}
		// attr("href") = 내가 선택한 값을 ${num}에 넣어라
		actionForm.submit();
	})
	//검색버튼을 클릭하면
	$("input[type='submit']").on("click", function(e){
		e.preventDefault();//실제로 구현할 때는 필요없음(test용)
		//pageNum의 값을 1로 설정
		actionForm.find("input[name='pageNum']").val("1")
		actionForm.submit();
	})
	
	
})
