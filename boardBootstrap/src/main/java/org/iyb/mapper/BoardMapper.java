package org.iyb.mapper;

import java.util.ArrayList;

import org.iyb.domain.BoardDTO;

public interface BoardMapper {
	//게시판 글쓰기와 관련되어있는 DB작업에 대한 설계
	public void write(BoardDTO board);
	
	//게시판 목록리스트와 관련되어있는 DB작업에 대한 설계
	public ArrayList<BoardDTO> list();
	
	//게시판 목록리스트에서 제목을 클릭했을 때 내용이 나오는 상세페이지와 관련되어있는 DB작업에 대한 설계
	public BoardDTO detail(BoardDTO board);
	
	//게시판 글수정과 관련되어있는 DB작업에 대한 설계
	public void modify(BoardDTO board);
	
	//게시판 글삭제와 관련되어있는 DB작업에 대한 설계
	public void remove(BoardDTO board);
}
