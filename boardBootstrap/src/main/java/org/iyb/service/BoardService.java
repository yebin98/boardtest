package org.iyb.service;

import java.util.ArrayList;

import org.iyb.domain.BoardDTO;

public interface BoardService { 
	//게시판 글쓰기
	public void write(BoardDTO board);
	//게시판 목록리스트
	public ArrayList<BoardDTO> list();
	//게시판 목록리스트에서 제목을 클릭했을 때 내용이 나오는 상세페이지
	public BoardDTO detail(BoardDTO board);
	//게시판 수정페이지
	public void modify(BoardDTO board);
	//게시판 삭제페이지
	public void remove(BoardDTO board);
}
