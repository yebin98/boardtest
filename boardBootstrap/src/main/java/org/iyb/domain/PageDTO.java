package org.iyb.domain;

public class PageDTO {
	private int startPage;//시작 페이지 번호
	private int endPage;//끝 페이지 번호
	private boolean prev;//이전 페이지 유무
	private boolean next;//다음 페이지 유무
	private int total;//board테이블의 총 데이터 건 수
	private Criteria cri;//endPage를 계산하기 위한 pageNum가 필요하므로 Criteria클래스를 포함
	
	public PageDTO(Criteria cri, int total){
		this.cri=cri;
		this.total=total;
		//(int)(Math.ceil(현제페이지번호/10.0))*10 계산된 결과를 endPage에 저장(전체건수를 고려하지 않음)
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage = endPage-9;//10-9=1, 20-9=11..... endPage가 변수에 없어 구분이 되기 때문에 this를 안 불여도 된다.
		//전체건수를 고려한 endPage->realEnd
		//(int)(Math.ceil(전체건수*1.0))/10; 1.0 : 소숫점까지 계산되기 위하여
		int realEnd=(int)(Math.ceil((total*1.0)/cri.getAmount()));
		//realEnd < endPage => realEnd값을 endPage에 저장
		if(realEnd<endPage) {
			this.endPage=realEnd;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage<realEnd;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	@Override
	public String toString() {
		return "PageDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
	
}
