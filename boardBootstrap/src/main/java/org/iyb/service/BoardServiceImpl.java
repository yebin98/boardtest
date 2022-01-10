package org.iyb.service;

import java.util.ArrayList;
import org.iyb.domain.BoardDTO;
import org.iyb.domain.Criteria;
import org.iyb.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService{ 
	@Autowired
	private BoardMapper bmapper;
	
	public void write(BoardDTO board) {
		//db 연결
		bmapper.write(board);
	}
	
	//게시판 목록리스트 설계된 것 구현
	public ArrayList<BoardDTO> list(Criteria cri) {
		return bmapper.list(cri);
	}
	
	@Transactional
	//게시판 목록리스트에서 제목을 클릭했을 때 내용이 나오는 상세페이지 설계된 것을 구현
	public BoardDTO detail(BoardDTO board) {
		bmapper.cntupdate(board);
		return bmapper.detail(board);
	}
	
	//게시판 글수정 설계된 것 구현
	public void modify(BoardDTO board) {
		bmapper.modify(board);
	}
	
	//게시판 글삭제 설계된 것 구현
		public void remove(BoardDTO board) {
			bmapper.remove(board);
		}
}
