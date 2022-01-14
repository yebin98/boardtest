package org.iyb.controller;

import org.iyb.domain.BoardDTO;
import org.iyb.domain.Criteria;
import org.iyb.domain.PageDTO;
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
	
	//글쓰기 화면으로
	@GetMapping("write")
	public void write() {}
	//글쓰기 버튼을 클릭하면
	@PostMapping("write")
	public String writePost(BoardDTO board) {
		service.write(board);
		return "redirect:/boardtest/list";
	}
	
	//게시판 목록 리스트
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", service.list(cri));
		int total = service.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri,total));//매개변수가 2개 이므로 select된 결과 도출
	}
	
	//게시판 목록 리스트에서 제목을 클릭하면
	@GetMapping("detail")
	public void detail(BoardDTO board, Model model) {
		model.addAttribute("detail", service.detail(board));
	}
	
	//글 수정 화면으로
	@GetMapping("modify")
	public void modify(BoardDTO board, Model model) {
		model.addAttribute("detail", service.detail(board));
	}
	
	//글수정 버튼을 클릭하면
	@PostMapping("modify")
	public String modifyPost(BoardDTO board, RedirectAttributes rttr) {
		//update
		service.modify(board);
		rttr.addAttribute("bno", board.getBno());
		return "redirect:/boardtest/detail";
	}
	
	//글삭제 버튼을 클릭하면
	@GetMapping("remove")
	public String remove(BoardDTO board) {
		//delete
		service.remove(board);
		return "redirect:/boardtest/list";
	}
}
