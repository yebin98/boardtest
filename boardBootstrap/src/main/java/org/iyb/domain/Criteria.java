package org.iyb.domain;

public class Criteria {
	private String search;
	private String keyword;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Criteria [search=" + search + ", keyword=" + keyword + "]";
	}
}
