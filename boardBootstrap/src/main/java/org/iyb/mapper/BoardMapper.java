package org.iyb.mapper;

import java.util.ArrayList;

import org.iyb.domain.BoardDTO;

public interface BoardMapper {
	//�Խ��� �۾���� ���õǾ��ִ� DB�۾��� ���� ����
	public void write(BoardDTO board);
	
	//�Խ��� ��ϸ���Ʈ�� ���õǾ��ִ� DB�۾��� ���� ����
	public ArrayList<BoardDTO> list();
	
	//�Խ��� ��ϸ���Ʈ���� ������ Ŭ������ �� ������ ������ ���������� ���õǾ��ִ� DB�۾��� ���� ����
	public BoardDTO detail(BoardDTO board);
	
	//�Խ��� �ۼ����� ���õǾ��ִ� DB�۾��� ���� ����
	public void modify(BoardDTO board);
	
	//�Խ��� �ۻ����� ���õǾ��ִ� DB�۾��� ���� ����
	public void remove(BoardDTO board);
}