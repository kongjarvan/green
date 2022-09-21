package site.metacoding.red.web.dto.request.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
	@Size(min=2, max=20, message = "username의 길이가 부적합합니다.")
	@NotBlank(message = "username이 null 이거나 공백일 수 없습니다.") // null과 공백 둘 다 검사
	@Pattern(regexp = "^[a-zA-Z0-9]{4,8}$", message = "username에는 한글이 들어올 수 없습니다.")
	private String username;
	@Size(min=2, max=20, message = "password의 길이가 부적합합니다.")
	@NotBlank(message = "password가 null 이거나 공백일 수 없습니다.")
	private String password;
	private boolean remember;
}
