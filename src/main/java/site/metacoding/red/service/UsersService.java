package site.metacoding.red.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@RequiredArgsConstructor
@Service
public class UsersService {

	private final UsersDao usersDao;
	private final BoardsDao boardsDao;

	public void 회원가입(JoinDto joinDto) { // username, password, email
		// 1.Dto를 Entity로 변경하는 코드
		Users users = joinDto.toEntity();
		// 2. Entity로 DB 수행
		usersDao.insert(users);
	}

	public Users 로그인(LoginDto loginDto) { // username, password
		Users usersPS = usersDao.findByUsername(loginDto.getUsername());
		
		if (usersPS==null) {
			return null;
		}

		// if로 usersPS의 password와 Dto password 비교
		if (usersPS.getPassword().equals(loginDto.getPassword())) {
			return usersPS;
		} else {
			return null;
		}
	}

	public Users 회원수정(Integer id, UpdateDto updateDto) { // id, Dto(password, email)
		// 1. 영속화
		Users usersPS = usersDao.findById(id);
		// 2. 영속화 된 객체 변경
		usersPS.update(updateDto);
		// 3. DB 수행
		usersDao.update(usersPS);
		
		return usersPS;
	}

	@Transactional(rollbackFor = RuntimeException.class) // 해당 메서드의 트랜젝션을 하나로 묶어줌, 하나라도 정상처리가 안되면 롤백
	public void 회원탈퇴(Integer id) {
		usersDao.deleteById(id);
		boardsDao.updateByUsersId(id); // 해당 회원이 적은 글을 모두 찾아서 usersId를 null로 업데이트(); forEach
	} // users - delete, boards - update

	public boolean 유저네임중복확인(String username) {
		Users usersPS = usersDao.findByUsername(username);
		// username이 있으면 true, 없으면 false 리턴

		if (usersPS == null) {
			return false;
		} else {
			return true;
		}
	}

	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}
	


}
