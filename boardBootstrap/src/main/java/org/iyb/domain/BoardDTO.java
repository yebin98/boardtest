package org.iyb.domain;

import java.util.ArrayList;

public class BoardDTO{
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private int cnt;
	private ArrayList<AttachFileDTO> attachList;//BoardDTO에 AttachFileDTO를 포함시킴
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public ArrayList<AttachFileDTO> getAttachList() {
		return attachList;
	}
	public void setAttachList(ArrayList<AttachFileDTO> attachList) {
		this.attachList = attachList;
	}
	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", cnt=" + cnt + ", attachList=" + attachList + "]";
	}
	
}
