package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;


@RequiredArgsConstructor
@Controller
public class BoardsController {
	
	private final BoardsService boardsService;
	private final HttpSession session;
	
	@PostMapping("/boards/{id}/update")
	public String Update(@PathVariable Integer id, UpdateDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return "redirect:/boards/" + id;
	}
	
	
	@GetMapping("/boards/{id}/updateForm")
	public String UpdateForm(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsService.게시글상세보기(id);
		model.addAttribute("boards", boardsPS);
		return "boards/updateForm";
	}
	
	
	@PostMapping("/boards")
	public String WriteBoard(WriteDto writeDto) {
		Users principal = (Users) session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, principal);		
		return "redirect:/";
	}
	
	
	@GetMapping("/boards/writeForm")
	public String writeForm(WriteDto writeDto) {
		return "boards/writeForm";
	}
	
	
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page, String keyword) {
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsService.게시글상세보기(id));
		return "boards/detail";
	}
	
	
	
	
	
}
