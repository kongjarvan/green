package site.metacoding.red.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.service.ex.MyException;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;


@RequiredArgsConstructor
@Service
public class BoardsService {

	
	private final BoardsDao boardsDao;
	private final LovesDao lovesDao;

	
	public void 좋아요취소(Integer lovesId) {
		lovesDao.deleteById(lovesId);
	}
	

	public Loves 좋아요(Loves loves) {
		lovesDao.insert(loves);
		return loves;
	}
	

	public PagingDto 게시글목록보기(Integer page, String keyword) {
		if (page == null) {
			page = 0;
		}
		int startNum = page * 5;
		List<MainDto> boardsList = boardsDao.findAll(startNum, keyword);
		PagingDto pagingDto = boardsDao.paging(page, keyword);
		if (boardsList.size() == 0)
			pagingDto.setNotResult(true);
		pagingDto.makeBlockInfo(keyword);
		pagingDto.setMainDtos(boardsList);

		return pagingDto;

	}

	
	public DetailDto 게시글상세보기(Integer id, Integer principalId) {
		return boardsDao.findByDetail(id, principalId);
	}

	
	public Boards 게시글수정화면가져오기(Integer id) {
		
		Boards boards = boardsDao.findById(id);
		
		if(boards == null) {
			throw new MyException(id+"의 게시글을 찾을 수 없습니다.");
		}
			
		return boards;
	}
	

	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id);
		
		if(boardsPS == null) {
			throw new RuntimeException(id+"를 찾을 수 없습니다.");
		}
		boardsPS.update(updateDto);
		boardsDao.update(boardsPS);
	}

	
	public void 게시글삭제하기(Integer id) {
		boardsDao.deleteById(id);
	}

	
	public void 게시글쓰기(WriteDto writeDto, Users principal) {
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}
}
