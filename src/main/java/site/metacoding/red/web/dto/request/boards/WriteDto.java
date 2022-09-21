package site.metacoding.red.web.dto.request.boards;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Setter
@Getter
public class WriteDto {
	@Size(min=1, max=150, message = "username의 길이가 부적합합니다.")
	@NotBlank(message = "title이 null 이거나 공백일 수 없습니다.")
	private String title;
	@NotBlank(message = "content이 null 이거나 공백일 수 없습니다.")
	private String content;
	
	public Boards toEntity(Integer usersId) {
		Boards boards = new Boards(this.title, this.content, usersId);
		return boards;
	}
}