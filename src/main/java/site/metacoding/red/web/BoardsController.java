package site.metacoding.red.web;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.CMRespDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;


@RequiredArgsConstructor
@Controller
public class BoardsController {
	
	private final BoardsService boardsService;
	private final HttpSession session;
	
	@PutMapping("/boards/{id}/update")
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
	public @ResponseBody CMRespDto<?> WriteBoard(@RequestBody WriteDto writeDto) {
		Users principal = (Users) session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, principal);		
		return new CMRespDto<>(1, "글쓰기 성공", null);
	}
	
	
	@GetMapping("/boards/writeForm")
	public String writeForm(WriteDto writeDto) {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
	
	
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page, String keyword) {
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	
	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsService.게시글상세보기(id));
		return "boards/detail";
	}
	
	
	@DeleteMapping("boards/{id}/delete")
	public String deleteById(@PathVariable Integer id) {
		return "redirect:/";
	}
	
	
}
