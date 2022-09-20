package site.metacoding.red.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.red.handler.HelloInterceptor;
import site.metacoding.red.handler.LoginInterceptor;

@Configuration // (웹 실행될때 new)
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/s/**"); // 주소를 보고 인터셉터를 실행시킴, /**: 모든 주소를 의미함 /*: 한 단계의 모든 주소를 의미함 (s/users, s/boards ...)
		// .addPathPatterns("/admin") : 여러개 걸 수 있음
		// .excludePathPatterns("/s/boards/**") : 해당 주소는 제외시킴 
		registry.addInterceptor(new HelloInterceptor())
		.addPathPatterns("/hello/**");
	}
}
