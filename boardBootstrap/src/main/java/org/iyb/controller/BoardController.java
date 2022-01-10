package org.iyb.controller;

import org.iyb.domain.BoardDTO;
import org.iyb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("boardtest")
public class BoardController {
	@Autowired
	private BoardService service;
	
	//�۾��� ȭ������
	@GetMapping("write")
	public void write() {}
	//�۾��� ��ư�� Ŭ���ϸ�
	@PostMapping("write")
	public String writePost(BoardDTO board) {
		service.write(board);
		return "redirect:/boardtest/list";
	}
	
	//�Խ��� ��� ����Ʈ
	@GetMapping("list")
	public void list(Model model) {
		service.list();
		model.addAttribute("list", service.list());
	}
	
	//�Խ��� ��� ����Ʈ���� ������ Ŭ���ϸ�
	@GetMapping("detail")
	public void detail(BoardDTO board, Model model) {
		model.addAttribute("detail", service.detail(board));
	}
	
	//�� ���� ȭ������
	@GetMapping("modify")
	public void modify(BoardDTO board, Model model) {
		model.addAttribute("detail", service.detail(board));
	}
	
	//�ۼ��� ��ư�� Ŭ���ϸ�
	@PostMapping("modify")
	public String modifyPost(BoardDTO board, RedirectAttributes rttr) {
		//update
		service.modify(board);
		rttr.addAttribute("bno", board.getBno());
		return "redirect:/boardtest/detail";
	}
	
	//�ۻ��� ��ư�� Ŭ���ϸ�
	@GetMapping("remove")
	public String remove(BoardDTO board) {
		//delete
		service.remove(board);
		return "redirect:/boardtest/list";
	}
}
