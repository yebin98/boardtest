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
		//db ����
		bmapper.write(board);
	}
	
	//�Խ��� ��ϸ���Ʈ ����� �� ����
	public ArrayList<BoardDTO> list(Criteria cri) {
		return bmapper.list(cri);
	}
	
	@Transactional
	//�Խ��� ��ϸ���Ʈ���� ������ Ŭ������ �� ������ ������ �������� ����� ���� ����
	public BoardDTO detail(BoardDTO board) {
		bmapper.cntupdate(board);
		return bmapper.detail(board);
	}
	
	//�Խ��� �ۼ��� ����� �� ����
	public void modify(BoardDTO board) {
		bmapper.modify(board);
	}
	
	//�Խ��� �ۻ��� ����� �� ����
		public void remove(BoardDTO board) {
			bmapper.remove(board);
		}
}
