package com.skhoct30.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.skhoct30.memo.common.FileManager;
import com.skhoct30.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/images/**") // images 로 시작하도록 접근해서 그 뒤에는 똑같이 ** 한다
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/"); // 실제 파일이 저장된 경로를 저장해야함.
		
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new PermissionInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/logout", "/static/**", "/image/**");
	}
	
	
}
