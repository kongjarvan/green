package site.metacoding.red.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.red.web.dto.response.CMRespDto;

public class HelloInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder(); // 2개 이상의 문자열을 처리 할때 StringBuilder 사용
		while (true) {
			String temp = br.readLine();
			if(temp == null) break;
			sb.append(temp); // append: 문자열 추가
		}
			if (sb.toString().contains("바보")) {
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "바보 아니다", null);
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(cmRespDto);
				out.println(json);
				return false;
			}		
			return true;

	}
}
