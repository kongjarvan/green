package site.metacoding.red.web;

import java.util.HashMap;
import java.util.Map;

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
	public @ResponseBody CMRespDto<?> Update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		session.getAttribute("search");
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "수정 성공", null);
	}
	
	
	@GetMapping("/boards/{id}/updateForm")
	public String UpdateForm(@PathVariable Integer id, Model model) {
		session.getAttribute("search");
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
		session.setAttribute("search", pagingDto);
		model.addAttribute("pagingDto", pagingDto);
		
		//	Map<String, Object> referer = new HashMap<>();
		//	referer.put("page", pagingDto.getCurrentPage());
		//	referer.put("keyword", pagingDto.getKeyword());
		//	session.setAttribute("referer", referer);
		return "boards/main";
	}
	
	
	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsService.게시글상세보기(id));
		return "boards/detail";
	}
	
	
	@DeleteMapping("boards/{id}/delete")
	public @ResponseBody CMRespDto<?> deleteById(@PathVariable Integer id) {
		session.getAttribute("search");
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "삭제 성공", null);
	}
	
	
}
