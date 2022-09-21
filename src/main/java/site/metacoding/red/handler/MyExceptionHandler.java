package site.metacoding.red.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import site.metacoding.red.service.ex.MyException;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.dto.response.CMRespDto;


@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(Exception.class) // exception의 자식중 어느것이든 발생하면 아래 메서드 실행
	public @ResponseBody CMRespDto<?> apiError(Exception e){
		return new CMRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(MyException.class)
	public @ResponseBody String m1(Exception e){
		return Script.back(e.getMessage());
	}
	
	
	
	
	
}
