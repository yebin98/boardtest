package org.iyb.service;

import java.util.ArrayList;

import org.iyb.domain.ReplyDTO;
import org.iyb.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper rmapper;
	//��� ���� ����� ���� ����
	public int write(ReplyDTO rdto) {
		return rmapper.write(rdto);
	}
	//��� ��� ����Ʈ ����� ���� ����
	public ArrayList<ReplyDTO> list(int bno) {
		return rmapper.list(bno);
	}
	
	//��� ������ �ϱ� ���� ��� ������ �������¼��踦 ����
	public ReplyDTO detail(int rno) {
		return rmapper.detail(rno);
	}
	
	//��� ���� ����� ���� ����
	public int update(ReplyDTO rdto) {
		return rmapper.update(rdto);
	}
	
	//��� ���� ����� ���� ����
	public int remove(ReplyDTO rdto) {
		return rmapper.remove(rdto);
	}
	
	
}
